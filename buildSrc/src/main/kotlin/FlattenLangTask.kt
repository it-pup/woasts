import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import java.io.File

// this sucks, but it works well enough
// can barely read this
abstract class FlattenLangTask : DefaultTask() {
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val inputDir: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:Input
    abstract val marker: Property<String>

    init {
        marker.convention("@")
    }

    private val gson: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()

    @TaskAction
    fun run() {
        val input = inputDir.get().asFile
        val output = outputDir.get().asFile
        val markerKey = marker.get()

        output.mkdirs()

        val files = input.listFiles { file ->
            file.isFile && file.extension.equals("json", true)
        } ?: emptyArray()

        if (files.isEmpty()) {
            logger.lifecycle("[!] no files found to flatten!")
            return
        }

        files.forEach { file ->
            val root = gson.fromJson(file.readText(), JsonObject::class.java)
            val result = JsonObject()

            processObject(root, "", markerKey, result)
            File(output, file.name).writeText(gson.toJson(result))
            logger.lifecycle("[+] processed ${file.name}")
        }
    }

    private fun processObject(
        obj: JsonObject,
        key: String,
        marker: String,
        result: JsonObject,
    ) {
        for ((k, e) in obj.entrySet()) {
            val newKey = if (key.isEmpty()) {
                k
            } else {
                "$key.$k"
            }

            processEntry(newKey, e, marker, result)
        }
    }

    private fun processEntry(
        key: String,
        entry: JsonElement,
        marker: String,
        result: JsonObject,
    ) {
        if (entry.isJsonObject) {
            processObject(entry.asJsonObject, key, marker, result)
            return
        }

        val prefix = key.removeSuffix(".$marker")
        val value = if (entry is JsonArray) JsonPrimitive(entry.joinToString("\n") { it.asString }) else entry

        result.add(prefix, value)
    }
}

