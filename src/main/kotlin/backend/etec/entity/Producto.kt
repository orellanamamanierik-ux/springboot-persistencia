package backend.etec.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "productos")
data class Producto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val nombre: String,

    @Column(nullable = false)
    val descripcion: String,

    @Column(nullable = false)
    val precio: BigDecimal,

    @Column(nullable = false)
    val stock: Int
)
