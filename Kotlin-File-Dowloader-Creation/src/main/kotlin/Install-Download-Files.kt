import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels


fun main() {
    installPy()
    installPip1()
}

// função para baixar um arquivo da URL e salvar no disco com o nome especificado
fun downloadArq(url: URL, NomeArqPip: String) {
    url.openStream().use { // abre a conexão com a url e cria um InputStream
        Channels.newChannel(it).use { rbc -> // cria um canal para ler
            FileOutputStream(NomeArqPip).use { fos -> // cria FileOutputStream para salvar
                fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE) // transfere
            }
        }
    }
}

fun installPip1() {
    val url = URL("https://bootstrap.pypa.io/get-pip.py") // link de onde baixa o pip
    val NomeArqPip = "get-pip.py" // nome do arquivo localmente
    downloadArq(url, NomeArqPip)
    println("Arquivo baixado com sucesso: $NomeArqPip")

    val nomeBash = "InstalarPip.bat"
    var arqBash = File(nomeBash) // cria um objeto File para o arquivo de script
    arqBash.writeText(
        "" // escreve o texto do bat aqui
    )
}

fun installPy() {
    val nomeBash = "InstalarPython.bat"
    var arqBash = File(nomeBash)
    arqBash.writeText(
        // escreve um comando de script para instalar o py usando chocolatey
        "@\"%SystemRoot%\\System32\\WindowsPowerShell\\v1.0\\powershell.exe\" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command \"iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))\" && SET \"PATH=%PATH%;%ALLUSERSPROFILE%\\chocolatey\\bin\"\n\n" +
                "choco install python311 --params \"/C:\\Users\\Public\""
    )
}

