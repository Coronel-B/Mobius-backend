group = "app.mobius"
version = "0.0.0"

//TODO: Inject secret hibernate credential
//open class LoadHibernateCredential : DefaultTask() {}
/*tasks.register<Copy>("copy") {
    from(file("$rootDir/${project.name}/file.txt"))
    into("$buildDir/newFile.txt")
}*/

/**
 * Sources:
 *  https://github.com/ronmamo/reflections
 */
dependencies {
    implementation(project(":domain"))

    implementation("org.reflections:reflections:0.9.12")
    // https://mvnrepository.com/artifact/com.vladmihalcea/hibernate-types-52
    implementation("com.vladmihalcea", "hibernate-types-52","2.10.0")


    implementation(project(":library_base"))
    implementation(project(":library_test_utils"))
    implementation(kotlin("reflect"))
}