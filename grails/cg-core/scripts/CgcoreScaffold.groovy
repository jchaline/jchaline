includeTargets << grailsScript("_GrailsInit")

def code = "confirm.ogilcore"
def confirmCount = 0
def confirmAll = false
def deleteAll = false

target(cgcoreScaffold: "Generation des templates de Scaffolding") {
    depends(checkVersion, parseArguments)
    String separator = File.separator

    event "StatusUpdate", ["[OGIL] Generation des templates de scaffolding OGIL."]

    // Copie des templates de Scaffolding
    sourceDir = "${cgCorePluginDir}${separator}src${separator}templates${separator}"
    targetDir = "${basedir}${separator}src${separator}templates${separator}"

    event "StatusUpdate", ["[CG-CORE] Copie : ${sourceDir} -> ${targetDir}."]
    if(!sourceDir.equals(targetDir)){
        copy(sourceDir, targetDir, "Copie des templates de Scaffolding dans src/templates", "copy-templates")
        event "StatusUpdate", ["[CG-CORE] Fin du script cgcoreScaffold."]
    }
    else{
        event "StatusUpdate", ["[CG-CORE] Pas de copie du core vers lui-même."]
    }


}

setDefaultTarget(cgcoreScaffold)

copy = {String source, String target, String confirmText, String confirmCode ->
    def overwrite = confirmAll
    def input = ""

    // only if dir already exists in, ask to overwrite it
    if (new File(target).exists()) {
        // TODO: copy existing files / dirs into a "trash" directory
        if (isInteractive && !overwrite)                        input = grailsConsole.userInput('Overwrite '+confirmText+'? ', ["y","n","a"] as String[])
        if (!isInteractive || input == "y" || input == "a" )    overwrite = true
        if (input == "a")                                       confirmAll = true
    } else {
        ant.mkdir(dir: target)
        overwrite = true    // nothing to overwrite but will be copied (state this in the event message)
    }

    // Filtering Demo* files and directories
    if (new File(source).isDirectory()) ant.copy(todir: "$target", overwrite: overwrite)
            {
                fileset(dir: "$source") {
                    exclude(name: "**/demo")
                }
            }
    else                                ant.copy(todir: "$target", overwrite: overwrite)
            {
                fileset(file: "$source") {
                    exclude(name: "**/Demo*.groovy")
                }
            }
    event "StatusUpdate", ["... ${confirmText} ${overwrite ? '' : 'not '}installed!"]
}