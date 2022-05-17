package masson.reynoso.tofi

data class Perfil(
    var nombre: String?,
    var edad: Int,
    var icono: Int,
    var temas: ArrayList<String>?,
    var libros: ArrayList<Libro>?
)
