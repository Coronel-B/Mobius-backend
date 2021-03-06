package app.mobius.io

import java.io.File
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path

object ParentPath {
    private const val COMMON_KOTLIN = "/kotlin"
    private const val COMMON_RESOURCES = "/resources"
    object Main {
        private const val MAIN = "/main"
        const val KOTLIN = "${MAIN}${COMMON_KOTLIN}"
        const val RESOURCES = "${MAIN}${COMMON_RESOURCES}"
    }
    object Test {
        private const val TEST = "/test"
        const val KOTLIN = "${TEST}${COMMON_KOTLIN}"
        const val RESOURCES = "${TEST}${COMMON_RESOURCES}"
    }
}

@ExperimentalPathApi
object ResourceUtils {

    /**
     * Create or update a file for differents OS
     * @param relPath: Relative to ${moduleName}/src/${srcType}/resources/
     * Source: https://stackoverflow.com/a/64084771/5279996
     */
    fun buildAbsolutePath(
            moduleName: String,
            parentPath: String,
            relPath: String
    ): String {
        val absolutePathCurrentModule = (Path("").toAbsolutePath() as Any).toString()
        val absolutePathProjectRoot = absolutePathCurrentModule.dropLastWhile { it != '/' }

        var absolutePath = "${absolutePathProjectRoot}${moduleName}/src${parentPath}$relPath"

//        Normalice path for OS
        when (System.getProperty("os.name")) {
            "Linux" -> {
                absolutePath = absolutePath.replace("\\", "/")
            }
            "Windows" -> {
                absolutePath = absolutePath.replace("/", "\\")
            }
            else -> { }
        }

        println("Absolute Path: $absolutePath") //TODO: Use Logger
        return absolutePath
    }

    /**
     * Create or update a file for differents OS
     * @param relPath: Relative to ${moduleName}/src/${srcType}/resources/
     */
    fun getFile(
            moduleName: String,
            parentPath: String,
            relPath: String
    ): File {
        return File(
                buildAbsolutePath(
                        moduleName = moduleName,
                        parentPath = parentPath,
                        relPath = relPath
                )
        )
    }



}