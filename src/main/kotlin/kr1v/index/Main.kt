@file:Suppress("FunctionName")

package kr1v.index

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import kotlinx.html.title
import kr1v.index.libs.Libraries
import kr1v.index.util.*
import kr1v.index.util.ConfigMethod.TypeOfClass
import kr1v.index.util.ConfigMethod.Waaa
import java.nio.file.Files
import java.nio.file.Path
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.abs
import kotlin.random.Random

fun ConfigLibrary.tags(): List<Pair<String, String>> {
    val tags = arrayListOf<Pair<String, String>>()

    if (side.client) tags.add(Pair("Client"))
    if (side.server) tags.add(Pair("Server"))
    if (type.ui) tags.add(Pair("Ui"))
    if (type.loader) tags.add(Pair("Loader"))

    extraFeatures.forEach { tags.add(Pair(it.name, it.description)) }
    configFormats.forEach { tags.add(Pair(it.name)) }

    return tags
}

fun Pair(first: String): Pair<String, String> {
    return Pair(first, "")
}

fun FlowContent.tag(titleStr: String, tooltip: String = "", onClickStr: String = "", spanStyle: String = "", h6Style: String = "") {
    span("tag") {
        if (spanStyle.isNotEmpty()) {
            style = spanStyle
        }
        if (onClickStr.isNotEmpty()) {
            onClick = onClickStr
        }
        h6 {
            if (tooltip.isNotEmpty()) {
                classes = setOf("hoverable")
                title = tooltip
            }
            style = h6Style.ifEmpty {
                "margin: 2px;"
            }
            +titleStr
        }
    }
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
        if (library.configMethod != ConfigMethod.NOT_AVAILABLE) {
            h4 {
                +"Config method: "
                if (library.configMethod == ConfigMethod.UNKNOWN) {
                    +"Unknown"
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

                tag(
                    "Show/hide",
                    onClickStr = """
			            const el = document.getElementById("example-config-${library.id}");
			            el.style.display = el.style.display === "none" ? "block" : "none";
		            """.trimIndent(),
                    spanStyle = "display: inline-block; margin-left: 0px; cursor: pointer;"
                )

                span {
                    style = "display: inline; margin-left: 4px;"
                    +":"
                }

                pre("codeSamp") {
                    id = "example-config-${library.id}"
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
            tag(it.first, it.second)
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


    val libsHtml = buildString {
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
                                tag(version, onClickStr = "toggleFilter('versions', '$version')", h6Style = "margin-left: 4px; margin: 2px;")
                            }
                        }

                        h4 {
                            +"Side"
                        }
                        tag("Client", onClickStr = "toggleFilter('side', 'CLIENT')")
                        tag("Server", onClickStr = "toggleFilter('side', 'SERVER')")

                        h4 {
                            +"Config types"
                        }
                        for (type in ConfigType.entries) {
                            tag(type.name, type.description, "toggleFilter('extraConfigTypes', '$type')")
                        }

                        h4 {
                            +"Features"
                        }
                        for (feature in Feature.entries) {
                            tag(feature.name, feature.description, "toggleFilter('extraFeatures', '$feature')")
                        }

                        h4 {
                            +"Config formats"
                        }
                        for (configFormat in ConfigFormat.entries) {
                            if (configFormat == ConfigFormat.NOT_AVAILABLE) continue
                            tag(configFormat.name, onClickStr = "toggleFilter('configFormats', '$configFormat')")
                        }

                        h4 {
                            +"Init mode"
                        }
                        for (mode in InitMode.entries) {
                            if (mode == InitMode.NOT_AVAILABLE || mode == InitMode.UNKNOWN) continue
                            tag(mode.name, onClickStr = "toggleFilter('manualInitialization', '$mode')")
                        }

                        h4 {
                            +"Config method"
                        }
                        h5 {
                            style = "margin: 0;"
                            +"Field kind"
                            tag("instance", onClickStr = "toggleFilter('configMethod.instance', 'true')")
                            tag("static", onClickStr = "toggleFilter('configMethod.instance', 'false')")
                        }
                        h5 {
                            style = "margin: 0;"
                            +"Type of fields"
                            for (waaa in Waaa.entries) {
                                tag(waaa.name, waaa.exampleText, "toggleFilter('configMethod.waaas', '$waaa')")
                            }
                        }
                    }

                    div("panel") {
                        style = "height: fit-content;"
                        div {
                            style = """
                                display: block;
                                margin-left: auto;
                                width: fit-content;
                                margin-right: 3px;
                                margin-top: 0px;
                                margin-bottom: -20px;
                            """.trimIndent()
                            +("Total libraries: " + Libraries.CONFIG_LIBRARIES().size)
                        }
                        a {
                            href = "https://kr1v.net/libs/libs.json"
                            +"View json"
                        }
                        br
                        a {
                            href = "https://kr1v.net/libs/facts"
                            +"View facts"
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

    Files.writeString(Path.of("generated", "index.html"), libsHtml)

    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu")

    val factOfTheDayHtml = buildString {
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
                    +"Fabric Config Library Facts"
                }
            }
            body {
                style = "font-size: 13px; color: #cdd6f4; margin: 0px"

                div("panel") {
                    style = "width: 66%; align: middle; margin-left: auto; margin-right: auto;"
                    h4 {
                        a {
                            href = "https://kr1v.net/libs"
                            +"Go to index"
                        }
                    }
                    h4 {
                        +"Have a suggestion? Send me a dm on discord: "
                        code {
                            +".kr1v"
                        }
                    }
                }

                for (fact in Facts.facts().reversed()) {
                    div("panel") {
                        style = "width: 66%; align: middle; margin-left: auto; margin-right: auto;"
                        div {
                            style = "opacity: 0.75; margin-bottom: 6px;"
                            h3 {
                                style = "margin: 0;"
                                +("Day " + (Facts.facts().indexOf(fact)+1) + " (")
                                time {
                                    dateTime = fact.date().toString()
                                    +fact.date().format(dateFormatter)
                                }
                                +")"
                            }
                        }
                        div {
                            style = """
                                font: helvetica;
                                overflow-wrap: anywhere;
                                word-break: break-word;
                            """.trimIndent()
                            unsafe {
                                +fact.fact()
                            }
                        }
                    }
                }
            }
        }
    }

    Files.writeString(Path.of("generated", "facts.html"), factOfTheDayHtml)
}
