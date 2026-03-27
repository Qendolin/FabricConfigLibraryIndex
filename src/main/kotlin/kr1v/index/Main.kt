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
import kotlin.math.abs
import kotlin.random.Random

fun ConfigLibrary.tags(): List<Pair<String, String>> {
    val tags = arrayListOf<Pair<String, String>>()

    if (side.client) tags.add(Pair("Client", ""))
    if (side.server) tags.add(Pair("Server", ""))
    if (type.ui) tags.add(Pair("Ui", ""))
    if (type.loader) tags.add(Pair("Loader", ""))

    extraFeatures.forEach { tags.add(Pair(it.name, it.description)) }
    configFormats.forEach { tags.add(Pair(it.name, "")) }

    return tags
}

fun FlowContent.ConfigLibraryPanel(library: ConfigLibrary) {
    div {
        classes = setOf("panel")

        attributes["id"] = library.id

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
            style = "display: inline-block; margin: 0;"
            +library.name
            +" ("
        }
        h2 {
            style = "display: inline-block; margin: 0;"
            code {
                span {
                    style = "padding: 3px;"
                    +library.id
                }
            }
        }
        h1 {
            style = "display: inline-block; margin: 0;"
            +")"
        }

        // dependencies
        if (library.dependencies.isNotEmpty()) {
            h4 {
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
            +"Manual initialization: "
            +library.manualInitialization.name
        }

        // config format
        h4 {
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

                val random = Random(library.name.hashCode())
                val length = method.waaas.size

                val examples = HashSet<String>()
                while (examples.size < 3) {
                    val exampless = method.waaas[abs(random.nextInt() % length)].examples
                    examples.add(exampless[abs(random.nextInt() % exampless.size)])
                }
                for (example in examples) {
                    span {
                        style = "display: inline-block; margin: 1px;"
                        code {
                            span {
                                style = "margin: 5px;"
                                +example
                            }
                        }
                    }
                }
            }
        }

        // config types
        if (library.extraConfigTypes.isNotEmpty()) {
            h4 {
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

        if (library.type.ui) {
            h4 {
                span {
                    if (library.uiMethod.description.isNotEmpty()) {
                        classes = setOf("hoverable")
                        title = library.uiMethod.description
                    }
                    +"Ui method: "
                    +library.uiMethod.name
                }
            }
        }

        div { style = "height: 5px" }

        // versions
        h4 {
            var isFirst = true
            +"Available for versions: "

            val set = Versions.ALL_SET
            val versions = ArrayList(library.versions)

            for (versionRange in set) {
                if (versions.containsAll(versionRange)) {
                    val version = versionRange.first().substring(0, versionRange.first().length-2) + ".x"
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
                +"Notes:"
            }
            h5 {
                style = "margin: 0;"
                +library.notes
            }
        }

        if (library.type.loader) {
            div {
                h4 {
                    style = "display: inline; margin: 0;"
                    +"Example config"
                }

                span("tag") {
                    style = "display: inline-block; margin-left: 0px; cursor: pointer;"
                    onClick = """
			            const el = document.getElementById("example-config-${library.id}");
			            el.style.display = el.style.display === "none" ? "block" : "none";
		            """.trimIndent()
                    h6 {
                        style = "margin: 2px;"
                        +"Show/hide"
                    }
                }

                span {
                    style = "display: inline; margin-left: 4px;"
                    +":"
                }

                pre {
                    id = "example-config-${library.id}"
                    style = """
                            margin-top: 8px;
                            display: none;
                            font-family: monospace;
                            color: #cba6f7;
                            background-color: #11111b;
                            padding: 3px;
                            margin: 2px;
                            width: fit-content;
                            padding-right: 1em;
                            border-left: 3px solid #cba6f7;
                        """.trimIndent()
                    samp {
                        if (library.exampleConfigClass == null) {
                            +"None yet! Contribute by providing an example "
                            a {
                                val link = "https://github.com/kr1viah/FabricConfigLibraryIndex/blob/master/src/main/java/kr1v/index/libs/" + library.javaClass.simpleName + ".java"
                                href = link
                                +"here"
                            }
                            +"."
                        } else {
                            unsafe {
                                +library.exampleConfigClass.replace("\t", "    ")
                            }
                        }
                    }
                }
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
    val dir = Path.of("generated", "json")

    Files.createDirectories(dir)

    val obj = JsonObject()

    for (library in Libraries.CONFIG_LIBRARIES()) {
        val fileName = library.id + ".json"

        obj.add(library.id, gson.toJsonTree(library))

        val path = dir.resolve(fileName)
        Files.writeString(path, gson.toJson(library))
    }

    Files.writeString(Path.of("generated", "libs.json"), gson.toJson(obj))


    val html = buildString {
        appendHTML().html {
            lang = "en"
            head {
                meta(charset = "UTF-8")
                style {
                    unsafe {
                        +Files.readString(Path.of("src/main/kotlin/kr1v/index/main.css"))
                    }
                }
                title {
                    +"Fabric Config Library Index"
                }
            }
            body {
                style = "font-size: 13px; color: #cdd6f4; margin: 0px; display: flex; height: 100%"

                div("sidebar") {
                    style = "flex: 1; display: flex; flex-direction: column; padding: 10px"

                    div("panel") {
                        style = "overflow-y: auto; height: max-content;"

                        div {
                            style = """
                        display: block;
                        margin-left: auto;
                        width: fit-content;
                        """.trimIndent()
                            onClick = "resetFilters()"
                            span {
                                style = """
                                background: #11111b;
                                border-style: solid;
                                border-color: #6c7086;
                                border-radius: 10px;
                                display: inline-block;
                                border-width: 2px;
                                margin-left: 3px;
                                padding-left: 2px;
                                padding-right: 2px;
                                margin-bottom: -15px;
                                color: #a6adc8;
                                height: 1em;
                            """.trimIndent()
                                h6 {
                                    style = "margin-top: 0.25em"
                                    +"Reset filters"
                                }
                            }
                        }
                        h4 {
                            +"Versions"
                        }
                        for (versionSet in Versions.ALL_SET.reversed()) {
                            h5 {
                                style = "margin-left: 2px; margin: 0"
                                +versionSet.first().substring(0, versionSet.first().length-2)
                            }
                            for (version in versionSet) {
                                span("tag") {
                                    onClick = "toggleFilter('versions', '$version')"
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
                            onClick = "toggleFilter('side', 'CLIENT')"
                            h6 {
                                style = "margin: 2px;"
                                +"Client"
                            }
                        }
                        span("tag") {
                            onClick = "toggleFilter('side', 'SERVER')"
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
                                onClick = "toggleFilter('extraConfigTypes', '$type')"
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
                                onClick = "toggleFilter('extraFeatures', '$feature')"
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
                                onClick = "toggleFilter('configFormats', '$configFormat')"
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
                                onClick = "toggleFilter('manualInitialization', '$mode')"
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
                                onClick = "toggleFilter('configMethod.instance', 'true')"
                                h6 {
                                    style = "margin: 2px;"
                                    +"instance"
                                }
                            }
                            span("tag") {
                                onClick = "toggleFilter('configMethod.instance', 'false')"
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
                                    onClick = "toggleFilter('configMethod.waaas', '$waaa')"
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

                    div("panel") {
                        style = "height: fit-content;"
                        a {
                            href = "https://kr1v.net/libs/libs.json"
                            +"View json"
                        }
                    }
                }

                div("library_grid") {
                    style = "flex: 2; overflow-y: auto; padding: 10px;"
                    Libraries.CONFIG_LIBRARIES().shuffled(Random(2131)).forEach {
                        ConfigLibraryPanel(it)
                    }
                }

                script {
                    unsafe {
                        +Files.readString(Path.of("src/main/kotlin/kr1v/index/main.js"))
                    }
                }
            }
        }
    }

    Files.writeString(Path.of("generated", "index.html"), html)
}
