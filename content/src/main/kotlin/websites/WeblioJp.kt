package websites

import Languages
import Languages.ENGLISH
import Languages.JAPANESE
import org.w3c.dom.HTMLSpanElement
import org.w3c.dom.HTMLTableCellElement
import kotlin.browser.document

class WeblioJp : TranslationWebsiteRegisterer() {

    override fun isValidPage() = isEnglish2Japanese() || isJapanese2English()
    private fun isEnglish2Japanese() = document.querySelector("#summary td.ej") != null
    private fun isJapanese2English() = document.querySelector("#summary td.je") != null

    override fun getLanguage(): Pair<Languages, Languages> {
        return if (isEnglish2Japanese()) Pair(ENGLISH, JAPANESE) else Pair(JAPANESE, ENGLISH)
    }

    override fun getSearchWord(): String {
        return (document.querySelector("#h1Query") as HTMLSpanElement).innerText
    }

    override fun getTranslation(): String {
        return if (isEnglish2Japanese()) {
            (document.querySelector("#summary td.ej") as HTMLTableCellElement).innerText
        } else {
            (document.querySelector("#summary td.je") as HTMLTableCellElement).innerText
        }
    }
}
