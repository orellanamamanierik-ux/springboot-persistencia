package backend.etec.service

import backend.etec.dto.ProductoRequest
import backend.etec.dto.ProductoResponse
import backend.etec.entity.Producto
import backend.etec.repository.ProductoRepository
import backend.etec.exception.ProductoNoEncontradoException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductoService(private val productoRepository: ProductoRepository) {

    @Transactional(readOnly = true)
    fun listar(pageable: Pageable): Page<ProductoResponse> {
        return productoRepository.findAll(pageable).map { productoToResponse(it) }
    }

    @Transactional(readOnly = true)
    fun buscarPorId(id: Long): ProductoResponse {
        val producto = productoRepository.findById(id)
            .orElseThrow { ProductoNoEncontradoException("Producto con id $id no encontrado") }
        return productoToResponse(producto)
    }

    @Transactional
    fun crear(request: ProductoRequest): ProductoResponse {
        val producto = Producto(
            nombre = request.nombre,
            descripcion = request.descripcion,
            precio = request.precio,
            stock = request.stock
        )
        val guardado = productoRepository.save(producto)
        return productoToResponse(guardado)
    }

    private fun productoToResponse(producto: Producto): ProductoResponse {
        return ProductoResponse(
            id = producto.id,
            nombre = producto.nombre,
            descripcion = producto.descripcion,
            precio = producto.precio,
            stock = producto.stock
        )
    }
}
