import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels
/**
 * codigo de criação de arquivos de dowloads de arquivos da internet com exemplos úteis, fácil de copiar
 * para aplicar em projetos que executa funções para realizar o download de um arquivo da internet dado
 * uma url e nome do arquivo de destino.
 * a função utiliza entrada e saída de streams para efetuar o download do arquivo e criar o arquivo local.
 * parametro  url a url do arquivo a ser baixado.
 * parametro nomeArqPip o nome do arquivo de destino onde o conteúdo será salvo.
 * além d utilização da criação de arquivos também fácil para a copia
 **/

fun main() {
    installPy()
    installPip1()
}

// função para baixar um arquivo da URL e salvar no disco com o nome especificado
fun downloadArq(url: URL, nomeArqPip: String) {
    url.openStream().use { // abre a conexão com a url e cria um InputStream
        Channels.newChannel(it).use { rbc -> // cria um canal para ler
            FileOutputStream(nomeArqPip).use { fos -> // cria FileOutputStream para salvar
                fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE) // transfere
            }
        }
    }
}

fun installPip1() {
    val url = URL("https://bootstrap.pypa.io/get-pip.py") // link de onde baixa o pip
    val nomeArqPip = "get-pip.py" // nome do arquivo localmente
    downloadArq(url, nomeArqPip)
    println("Arquivo baixado com sucesso: $nomeArqPip")

    val nomeBash = "InstalarPip.bat"
    val arqBash = File(nomeBash) // cria um objeto File para o arquivo de script
    arqBash.writeText(
        "" // escreve o texto do bat aqui
    )
}

fun installPy() {
    val nomeBash = "InstalarPython.bat"
    val arqBash = File(nomeBash)
    arqBash.writeText(
        // escreve um comando de script para instalar o py usando chocolatey
        "@\"%SystemRoot%\\System32\\WindowsPowerShell\\v1.0\\powershell.exe\" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command \"iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))\" && SET \"PATH=%PATH%;%ALLUSERSPROFILE%\\chocolatey\\bin\"\n\n" +
                "choco install python311 --params \"/C:\\Users\\Public\""
    )
}

