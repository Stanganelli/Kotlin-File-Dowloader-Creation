import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

fun main() {
    installPy()
    installPip1()
}


fun downloadArq(url: URL, NomeArqPip: String) {
    url.openStream().use {
        Channels.newChannel(it).use { rbc ->
            FileOutputStream(NomeArqPip).use { fos ->
                fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
            }
        }
    }
}

fun installPip1() {
    val url = URL("https://bootstrap.pypa.io/get-pip.py")
    val NomeArqPip = "get-pip.py"
    downloadArq(url, NomeArqPip)
    println("Arquivo baixado com sucesso: $NomeArqPip")


    val nomeBash = "InstalarPip.bat"
    var arqBash = File(nomeBash)
    arqBash.writeText(
        ""
    )
}

fun installPy() {
    val nomeBash = "InstalarPython.bat"
    var arqBash = File(nomeBash)
    arqBash.writeText(
        "@\"%SystemRoot%\\System32\\WindowsPowerShell\\v1.0\\powershell.exe\" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command \"iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))\" && SET \"PATH=%PATH%;%ALLUSERSPROFILE%\\chocolatey\\bin\"\n\n" +
                "choco install python311 --params \"/C:\\Users\\Public\""
    )
}

