@file:Suppress("FunctionName")

package kr1v.index

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import kr1v.index.libs.Libraries
import kr1v.index.util.*
import kr1v.index.util.ConfigMethod.TypeOfClass
import kr1v.index.util.ConfigMethod.Waaa
import java.nio.file.Files
import java.nio.file.Path
import java.util.Locale
import java.util.Random
import kotlin.math.abs

fun ConfigLibrary.tags(): List<Pair<String, String>> {
    val tags = arrayListOf<Pair<String, String>>()

    if (side.client) tags.add(Pair("Client", ""))
    if (side.server) tags.add(Pair("Server", ""))
    if (type.gui) tags.add(Pair("Gui", ""))
    if (type.loader) tags.add(Pair("Loader", ""))

    extraFeatures.forEach { tags.add(Pair(it.name, it.description)) }
    configFormats.forEach { tags.add(Pair(it.name, "")) }

    return tags
}

fun FlowContent.ConfigLibraryPanel(library: ConfigLibrary) {
    div {
        classes = setOf("panel")

        attributes["id"] = library.id
        attributes["versions"] = library.versions.joinToString { it }

        attributes["side_client"] = library.side.client.toString()
        attributes["side_server"] = library.side.server.toString()
        attributes["type_loader"] = library.type.loader.toString()
        attributes["type_gui"] = library.type.gui.toString()

        attributes["config_types"] = library.extraConfigTypes.joinToString(",", transform = { it.name + (if (it.description.isEmpty())  "" else ("$" + it.description)) })
        attributes["features"] = library.extraFeatures.joinToString(",", transform = { it.name + (if (it.description.isEmpty())  "" else ("$" + it.description)) })
        attributes["config_formats"] = library.configFormats.joinToString(",", transform = { it.name })
        attributes["init_mode"] = library.manualInitialization.name

        // links
        div {
            style = """
                	display: block;
                	margin-left: auto;
                	width: fit-content;
                    margin-right: 3px;
                    margin-top: 0px;
                    margin-bottom: -20px;
            """.trimIndent()
            a {
                href = "https://github.com/kr1viah/FabricConfigLibraryIndex/blob/master/src/main/java/kr1v/index/libs/" + library.javaClass.simpleName + ".java"
                +"View entry"
            }
            +" "
            a {
                href = library.source
                +"Source code"
            }
        }

        // title
        h1 {
            style = "display: inline-block; margin: 0px; margin-left: 3px;"
            +library.name
            +" ("
        }
        h2 {
            style = "display: inline-block; margin: 1px;"
            code {
                span {
                    style = "margin: 5px;"
                    +library.id
                }
            }
        }
        h1 {
            style = "display: inline-block; margin: 0px;"
            +")"
        }

        // dependencies
        if (library.dependencies.isNotEmpty()) {
            h4 {
                style = "margin-left: 3px;"
                var isFirst = true
                +"Dependencies: "
                for (dep in library.dependencies) {
                    if (!isFirst) {
                        +", "
                    }
                    a {
                        href = dep.url
                        +dep.name
                    }
                    isFirst = false
                }
            }
        }

        // init mode
        h4 {
            style = "margin-left: 3px;"
            +"Manual initialization: "
            +library.manualInitialization.name
        }

        // config format
        h4 {
            style = "margin-left: 3px;"
            +"Config method: "
            if (library.configMethod == ConfigMethod.UNKNOWN) {
                +"Unknown"
            } else if (library.configMethod == ConfigMethod.NOT_AVAILABLE) {
                +"n/a"
            } else {
                val method = library.configMethod
                if (method.typeOfClass != TypeOfClass.NONE) {
                    +"a"
                    if (method.typeOfClass == TypeOfClass.EXTENDING || method.typeOfClass == TypeOfClass.ANNOTATED) +"n"
                    +" ${method.typeOfClass.name.lowercase(Locale.ROOT)} class with "
                }
                if (method.waaas.size != 1) {
                    +"either "
                }
                for (waaa in method.waaas) {
                    if (waaa == Waaa.ANNOTATED_PRIMITIVE) {
                        +"annotated "
                    }
                    if (method.instance) {
                        +"instance members"
                    } else {
                        +"static members"
                    }
                    when (waaa) {
                        Waaa.PRIMITIVE, Waaa.ANNOTATED_PRIMITIVE -> +(", of primitive type")
                        Waaa.WRAPPER -> +(", typed with wrappers")
                        Waaa.SPECIAL -> +(", typed with special classes")
                    }

                    if (method.waaas.indexOf(waaa) < method.waaas.size-1) {
                        +", or "
                    }
                }

                br()

                +"Examples: "

                val random = kotlin.random.Random(library.id.hashCode())
                val length = method.waaas.size

                repeat((1..3).count()) {
                    span {
                        style = "display: inline-block; margin: 1px;"
                        code {
                            span {
                                style = "margin: 5px;"
                                val examples = method.waaas[abs(random.nextInt() % length)].examples

                                +examples[abs(random.nextInt() % examples.size)]
                            }
                        }
                    }
                }
            }
        }

        // config types
        if (library.extraConfigTypes.isNotEmpty()) {
            h4 {
                style = "margin-left: 3px;"
                var isFirst = true
                +"Extra config type(s) supported: "
                for (type in library.extraConfigTypes) {
                    if (!isFirst) {
                        +", "
                    }
                    span {
                        if (type.description.isNotEmpty()) {
                            classes = setOf("hoverable")
                            title = type.description
                        }
                        +type.name
                    }
                    isFirst = false
                }
            }
        }

        if (library.type.gui) {
            h4 {
                if (library.guiMethod.description.isNotEmpty()) {
                    classes = setOf("hoverable")
                    title = library.guiMethod.description
                }
                style = "margin-left: 3px;"
                +"Gui method: "
                +library.guiMethod.name
            }
        }

        div { style = "height: 5px" }

        // versions
        h4 {
            style = "margin-left: 3px;"
            var isFirst = true
            +"Available for versions: "

            val set = Versions.ALL_SET
            val versions = ArrayList(library.versions)

            for (versionRange in set) {
                if (versions.containsAll(versionRange)) {
                    val version = versionRange.first().removeSuffix(".0") + ".x"
                    versions.replaceAll {
                        if (it.equals(versionRange.first())) version
                        else it
                    }
                    versions.removeAll(versionRange)
                }
            }

            for (version in versions) {
                if (!isFirst) {
                    +", "
                }
                +version
                isFirst = false
            }
        }

        div { style = "height: 5px" }

        if (library.notes.isNotEmpty()) {
            h4 {
                style = "margin-left: 3px;"
                +"Notes:"
            }
            h5 {
                style = "margin: 0; margin-left: 3px; "
                +library.notes
            }
        }


        div { style = "height: 5px" }

        // tags
        library.tags().forEach {
            span("tag") {
                h6 {
                    if (it.second.isNotEmpty()) {
                        classes = setOf("hoverable")
                        title = it.second
                    }
                    style = "margin: 2px;"
                    +it.first
                }
            }
        }
    }
}

fun main() {
    val gson = GsonBuilder().setPrettyPrinting().serializeNulls().create()
    val dir = Path.of("json")

    Files.createDirectories(dir)

    val obj = JsonObject()

    for (library in Libraries.CONFIG_LIBRARIES()) {
        val fileName = library.id + ".json"

        obj.add(library.id, gson.toJsonTree(library))

        val path = dir.resolve(fileName)
        Files.writeString(path, gson.toJson(library))
    }

    Files.writeString(Path.of("libs.json"), gson.toJson(obj))


    val html = buildString {
        appendHTML().html {
            lang = "en"
            head {
                meta (charset = "UTF-8")
                style {
                    unsafe {
                        +Files.readString(Path.of("src/main/kotlin/kr1v/index/main.css"))
                    }
                }
                title {
                    +"Config Library Index"
                }
            }
            body {
                style = "font-size: 13px; color: var(--color-text-primary); margin: 0; display: flex; height: 100%"

                div("sidebar panel") {
                    style = "flex: 1; margin: 20px; padding: 2px; overflow-y: auto;"
                    h4 {
                        +"Versions"
                    }
                    for (versionSet in Versions.ALL_SET.reversed()) {
                        h5 {
                            style = "margin-left: 2px; margin: 0"
                            +versionSet.first().removeSuffix(".0")
                        }
                        for (version in versionSet) {
                            span("tag") {
                                h6 {
                                    style = "margin-left: 4px; margin: 2px;"
                                    +version
                                }
                            }
                        }
                    }

                    h4 {
                        +"Side"
                    }
                    span("tag") {
                        h6 {
                            style = "margin: 2px;"
                            +"Client"
                        }
                    }
                    span("tag") {
                        h6 {
                            style = "margin: 2px;"
                            +"Server"
                        }
                    }

                    h4 {
                        +"Config types"
                    }
                    for (type in ConfigType.entries) {
                        span("tag") {
                            h6 {
                                if (type.description.isNotEmpty()) {
                                    classes = setOf("hoverable")
                                    title = type.description
                                }
                                style = "margin: 2px;"
                                +type.name
                            }
                        }
                    }

                    h4 {
                        +"Features"
                    }
                    for (feature in Feature.entries) {
                        span("tag") {
                            h6 {
                                if (feature.description.isNotEmpty()) {
                                    classes = setOf("hoverable")
                                    title = feature.description
                                }
                                style = "margin: 2px;"
                                +feature.name
                            }
                        }
                    }

                    h4 {
                        +"Config formats"
                    }
                    for (configFormat in ConfigFormat.entries) {
                        if (configFormat == ConfigFormat.NOT_AVAILABLE) continue
                        span("tag") {
                            h6 {
                                style = "margin: 2px;"
                                +configFormat.name
                            }
                        }
                    }

                    h4 {
                        +"Init mode"
                    }
                    for (mode in InitMode.entries) {
                        if (mode == InitMode.NOT_AVAILABLE || mode == InitMode.UNKNOWN) continue
                        span("tag") {
                            h6 {
                                style = "margin: 2px;"
                                +mode.name
                            }
                        }
                    }

                    h4 {
                        +"Config method"
                    }
                    h5 {
                        style = "margin: 0;"
                        +"Field kind"
                        span("tag") {
                            h6 {
                                style = "margin: 2px;"
                                +"instance"
                            }
                        }
                        span("tag") {
                            h6 {
                                style = "margin: 2px;"
                                +"static"
                            }
                        }
                    }
                    h5 {
                        style = "margin: 0;"
                        +"Type of fields"
                        for (waaa in Waaa.entries) {
                            span("tag") {
                                h6 {
                                    style = "margin: 2px;"
                                    classes = setOf("hoverable")
                                    title = waaa.getExampleText()
                                    +waaa.name
                                }
                            }
                        }
                    }
                }

                div("library_grid") {
                    style = "flex: 2; overflow-y: auto; padding: 10px;"
                    Libraries.CONFIG_LIBRARIES().shuffled(Random(2131)).forEach {
                        ConfigLibraryPanel(it)
                    }
                }
            }
        }
    }

//    val html = createHTMLDocument().body {
//        style = "font-size: 13px; color: var(--color-text-primary); margin: 0;"
//
//        Libraries.CONFIG_LIBRARIES().forEach {
//            ConfigLibraryPanel(it)
//        }
//    }

    Files.writeString(Path.of("index.html"), html)
}
