package utils.reportGeneration

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.io.FileUtils
import org.apache.poi.xwpf.usermodel.XWPFDocument
import utils.Utils
import java.awt.Desktop
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.util.*
import javax.swing.JFileChooser
import kotlin.io.path.readAttributes

object WordGenerator {
    private val DOCUMENTS_DIRECTORY: String = JFileChooser().fileSystemView.defaultDirectory.toString() + "/Reports"
    private const val DAYS_TO_KEEP: Int = 30

    suspend fun generateReport(document: XWPFDocument, name: String) {
        val folderPath = generateCurrentDayFolder()
        val fileName = "${name}_${System.currentTimeMillis()}.docx"
        val filePath = "$folderPath/$fileName"
        writeDocumentToDisk(document, filePath)
    }

    fun clearPreviousFolders() {
        val thresholdTime = Calendar.getInstance().apply {
            add(Calendar.DATE, -DAYS_TO_KEEP)
        }.timeInMillis
        val directories = File(DOCUMENTS_DIRECTORY).listFiles(File::isDirectory)
        directories?.let {
            for (file in directories) {
                val attr = Files.readAttributes(file.toPath(), BasicFileAttributes::class.java)
                if (attr.creationTime().toMillis() < thresholdTime) FileUtils.deleteDirectory(file)
            }
        }
    }

    private fun generateCurrentDayFolder(): String {
        val currentDate = Utils.getFolderDate(Date())
        val path = "$DOCUMENTS_DIRECTORY/$currentDate"
        try {
            File(path).mkdirs()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return path
    }

    private suspend fun writeDocumentToDisk(document: XWPFDocument, filePath: String) {
        val file = File(filePath)
        val out = withContext(Dispatchers.IO) {
            FileOutputStream(file)
        }
        document.write(out)
        document.close()
        withContext(Dispatchers.IO) {
            out.close()
        }
        withContext(Dispatchers.IO) {
            Desktop.getDesktop().open(file)
        }
    }
}
