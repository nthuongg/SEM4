import 'package:http/http.dart' as http;
import 'dart:convert';
import '../models/product.dart';

class ProductService {
  final String baseUrl = 'https://t2210m-flutter.onrender.com/products';

  Future<List<Product>> fetchProducts() async {
    try {
      final response = await http.get(Uri.parse(baseUrl));

      if (response.statusCode == 200) {
        List<dynamic> jsonList = jsonDecode(response.body);
        return jsonList.map((json) => Product.fromJson(json)).toList();
      } else {
        // Log the response body for debugging
        print('Error fetching products: ${response.body}');
        throw Exception('Failed to load products');
      }
    } catch (e) {
      print('Exception: $e');
      throw Exception('An error occurred while fetching products');
    }
  }

  Future<void> createProduct(Product product) async {
    try {
      // Remove id from the product JSON to ensure it's not sent if auto-generated
      Map<String, dynamic> productJson = product.toJson();
      productJson.remove('id');

      final response = await http.post(
        Uri.parse(baseUrl),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode(productJson),
      );

      if (response.statusCode == 201) {
        print('Product created successfully');
      } else {
        // Log the response body for more insight
        print('Error creating product: ${response.body}');
        throw Exception('Failed to create product');
      }
    } catch (e) {
      print('Exception: $e');
      throw Exception('An error occurred while creating the product');
    }
  }

  Future<void> updateProduct(String id, Product product) async {
    try {
      final response = await http.put(
        Uri.parse('$baseUrl/$id'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode(product.toJson()),
      );

      if (response.statusCode == 200) {
        print('Product updated successfully');
      } else {
        print('Error updating product: ${response.body}');
        throw Exception('Failed to update product');
      }
    } catch (e) {
      print('Exception: $e');
      throw Exception('An error occurred while updating the product');
    }
  }

  Future<void> deleteProduct(String id) async {
    try {
      final response = await http.delete(Uri.parse('$baseUrl/$id'));

      // Check for both 200 OK and 204 No Content
      if (response.statusCode == 200 || response.statusCode == 204) {
        print('Product deleted successfully');
      } else {
        print('Error deleting product: ${response.body}');
        throw Exception('Failed to delete product');
      }
    } catch (e) {
      print('Exception: $e');
      throw Exception('An error occurred while deleting the product');
    }
  }
}
