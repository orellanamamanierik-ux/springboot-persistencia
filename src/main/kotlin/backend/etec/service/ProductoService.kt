package backend.etec.service

import backend.etec.dto.ProductoRequest
import backend.etec.dto.ProductoResponse
import backend.etec.entity.Producto
import backend.etec.exception.ProductoNoEncontradoException
import backend.etec.repository.ProductoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductoService(
    private val productoRepository: ProductoRepository
) {

    @Transactional(readOnly = true)
    fun listar(pageable: Pageable): Page<ProductoResponse> {
        return productoRepository.findAll(pageable)
            .map { ProductoResponse.fromEntity(it) }
    }

    @Transactional(readOnly = true)
    fun obtener(id: Long): ProductoResponse {
        val producto = productoRepository.findById(id)
            .orElseThrow { ProductoNoEncontradoException(id) }
        return ProductoResponse.fromEntity(producto)
    }

    @Transactional
    fun crear(req: ProductoRequest): ProductoResponse {
        val nuevoProducto = Producto(
            nombre = req.nombre,
            precio = req.precio,
            stock = req.stock
        )
        val guardado = productoRepository.save(nuevoProducto)
        return ProductoResponse.fromEntity(guardado)
    }
}
