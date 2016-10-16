package de.fh_kiel.annotation_processor;

import com.squareup.javapoet.*;
import de.fh_kiel.annotations.Boilerplate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Annotation processor using the {@link Boilerplate} annotation.
 *
 * @author Created by tom on 03.10.2016.
 */
@SupportedAnnotationTypes("de.fh_kiel.annotations.Boilerplate")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class BoilerplateProcessor extends AbstractProcessor {

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment
            roundEnv) {

        final Messager messager = processingEnv.getMessager();
        final Elements elementUtils = processingEnv.getElementUtils();
        final Filer filer = processingEnv.getFiler();

        try {
            for (final Element annotatedElement : roundEnv.getElementsAnnotatedWith(Boilerplate.class)) {
                final TypeElement typeElement = (TypeElement) annotatedElement;
                generateCode(typeElement, elementUtils, filer);
            }
            return true;
        } catch (final IOException ex) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Could not write sourcefile. " + ex
                    .getLocalizedMessage());
            return false;
        }
    }

    private void generateCode(final TypeElement typeElement, final Elements elementUtils, final Filer
            filer) throws IOException {
        final TypeElement superClassName = elementUtils.getTypeElement(typeElement.getQualifiedName());
        final String className = superClassName.getSimpleName().toString().replace("Stub", "");


        // first, get all the fields of the annotated class
        final List<FieldSpec> allFields = new ArrayList<>();
        final List<MethodSpec> allGettersAndSetters = new ArrayList<>();

        for (final Element elem : typeElement.getEnclosedElements()) {
            if (elem.getKind() == ElementKind.FIELD) {

                final FieldSpec fs = FieldSpec.builder(TypeName.get(elem.asType()), elem.getSimpleName
                        ().toString(), elem.getModifiers().toArray(new Modifier[elem.getModifiers()
                        .size()])).build();
                allFields.add(fs);

                final MethodSpec getter = MethodSpec.methodBuilder("get" + StringUtils.capitalize(elem
                        .getSimpleName().toString()))
                        .addModifiers(Modifier.PUBLIC)
                        .returns(TypeName.get(elem.asType()))
                        .addStatement("return this.$N", fs.name)
                        .build();
                allGettersAndSetters.add(getter);

                final MethodSpec setter = MethodSpec.methodBuilder("set" + StringUtils.capitalize(elem
                        .getSimpleName().toString()))
                        .addModifiers(Modifier.PUBLIC)
                        .returns(TypeName.VOID)
                        .addParameter(TypeName.get(elem.asType()), fs.name, Modifier.FINAL)
                        .addStatement("this.$N = $N", fs.name, fs.name)
                        .build();
                allGettersAndSetters.add(setter);
            }
        }

        final MethodSpec cons1 = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC).build();
        final MethodSpec.Builder builder = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC);
        for (final FieldSpec fs : allFields) {
            builder.addParameter(fs.type, fs.name, Modifier.FINAL)
                    .addStatement("this.$N = $N", fs.name, fs.name);
        }
        final MethodSpec cons2 = builder.build();


        final TypeSpec type = TypeSpec.classBuilder(className).addModifiers(Modifier.PUBLIC)
                .addSuperinterface(Serializable.class)
                .addAnnotation(AnnotationSpec.builder(Generated.class).addMember("value", "$S",
                        "annotation processor").build())
                .addFields(allFields)
                .addMethod(cons1)
                .addMethod(cons2)
                .addMethods(allGettersAndSetters)
                .addMethod(MethodSpec.methodBuilder("equals").addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .returns(TypeName.BOOLEAN)
                        .addParameter(Object.class, "o", Modifier.FINAL)
                        .beginControlFlow("if (!(o instanceof $N))", className)
                        .addStatement("return false")
                        .endControlFlow()
                        .addStatement("final $N checkPerson = ($N) o", className, className)
                        .addStatement("return new $T().append(this.id, checkPerson.getId()).isEquals()",
                                EqualsBuilder.class)
                        .build())
                .addMethod(MethodSpec.methodBuilder("hashCode").addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .returns(TypeName.INT)
                        .addStatement("return new $T().append(this.id).toHashCode()",
                                HashCodeBuilder.class)
                        .build()
                )
                .addMethod(MethodSpec.methodBuilder("toString").addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .returns(String.class)
                        .addStatement("return $T.reflectionToString(this)", ToStringBuilder.class)
                        .build()
                )
                .build();

        final PackageElement pkg = elementUtils.getPackageOf(superClassName);
        final JavaFile jf = JavaFile.builder(pkg.getQualifiedName().toString(), type).build();
        jf.writeTo(filer);
    }
}