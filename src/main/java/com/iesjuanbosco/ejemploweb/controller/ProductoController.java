package com.iesjuanbosco.ejemploweb.controller;

import com.iesjuanbosco.ejemploweb.entity.Producto;
import com.iesjuanbosco.ejemploweb.repository.ProductoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

//Para que Spring sepa que esta clase es un controlador tenemos que añadir la anotación @Controller justo antes de la clase
@Controller //Anotación que le indica a Spring que esta clase es un controlador
public class ProductoController {
    //para acceder al repositorio creamos una proppiedad y la asignamos en el constructor
    private ProductoRepository productoRepository;
    public ProductoController(ProductoRepository repository){
        this.productoRepository = repository;
    }
    /* COn la anotación GetMapping le indicamos a Spring que el siguiente método se va a ejecutar cuando el usuario acceda a la URL GET  http://localhost/productos */
    @GetMapping("/productos")
    public String findAll(Model model) {
        List<Producto> productos=  this.productoRepository.findAll();
        //PASAMOS LOS DATOS A LA VISTA
        model.addAttribute("productos", productos);
        return "producto-list";
    }
    //COmentario de prueba
    @GetMapping("/productos/add")
    public String add() {
        List<Producto> productos=new ArrayList<Producto>();
        Producto p1= new Producto (null, "Producto 1", 20, 45.5);
        Producto p2= new Producto (null, "Producto 2", 50, 45.5);
        Producto p3= new Producto (null, "Producto 3", 30, 45.5);
        Producto p4= new Producto (null, "Producto 4", 10, 30.0);
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);

        //Guardamos todos los productos en la base de datos utilizando el bjeto productoRepository
        this.productoRepository.saveAll(productos);

        //Redidige al controlador /productos
        return "redirect:/productos";
    }
    @PostMapping("/productos")
    public String addProducto() {
        return "producto-add";
    }


}