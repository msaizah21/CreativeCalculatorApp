import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _displayText = MutableLiveData<String>()
    val displayText: LiveData<String> = _displayText

    fun addLetter(letter: String) {
        val currentText = _displayText.value ?: ""
        _displayText.value = currentText + letter
    }

    fun clearText() {
        _displayText.value = ""
    }

    fun encode() {
        val currentText = _displayText.value ?: ""
        _displayText.value = currentText.map { char ->
            when {
                char.isLetter() -> (char.code + 1).toChar()
                else -> char
            }
        }.joinToString("")
    }
}