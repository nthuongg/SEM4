import 'package:flutter/material.dart';
import '../models/product.dart';
import '../services/product_service.dart';
import 'product_form_page.dart';

class ProductListPage extends StatefulWidget {
  @override
  _ProductListPageState createState() => _ProductListPageState();
}

class _ProductListPageState extends State<ProductListPage> {
  final ProductService productService = ProductService();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Product List'),
        actions: [
          IconButton(
            icon: Icon(Icons.add),
            onPressed: () async {
              final newProduct = await Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => ProductFormPage()),
              );
              if (newProduct != null) {
                setState(() {}); // Cập nhật danh sách sau khi thêm sản phẩm
              }
            },
          ),
        ],
      ),
      body: FutureBuilder<List<Product>>(
        future: productService.fetchProducts(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          }
          if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          }
          final products = snapshot.data ?? [];
          return ListView.builder(
            itemCount: products.length,
            itemBuilder: (context, index) {
              final product = products[index];
              return ListTile(
                title: Text(product.name),
                subtitle: Text(product.description),
                trailing: Row(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    IconButton(
                      icon: Icon(Icons.edit),
                      onPressed: () async {
                        final updatedProduct = await Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) =>
                                ProductFormPage(product: product),
                          ),
                        );
                        if (updatedProduct != null) {
                          setState(() {}); // Cập nhật danh sách sau khi chỉnh sửa
                        }
                      },
                    ),
                    IconButton(
                      icon: Icon(Icons.delete),
                      onPressed: () async {
                        bool? confirmDelete = await showDialog(
                          context: context,
                          builder: (context) => AlertDialog(
                            title: Text('Confirm Delete'),
                            content: Text('Are you sure you want to delete this product?'),
                            actions: [
                              TextButton(
                                child: Text('Cancel'),
                                onPressed: () => Navigator.pop(context, false),
                              ),
                              TextButton(
                                child: Text('Delete'),
                                onPressed: () => Navigator.pop(context, true),
                              ),
                            ],
                          ),
                        );

                        if (confirmDelete == true) {
                          await productService.deleteProduct(product.id);
                          setState(() {}); // Cập nhật danh sách sau khi xóa
                        }
                      },
                    ),
                  ],
                ),
                onTap: () {
                  // Điều hướng tới trang chi tiết sản phẩm nếu cần
                },
              );
            },
          );
        },
      ),
    );
  }
}