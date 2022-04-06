package masson.reynoso.tofi.ui.actividades

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActividadesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is actividades Fragment"
    }
    val text: LiveData<String> = _text
}