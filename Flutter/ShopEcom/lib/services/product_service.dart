import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/product.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

// class ProductService {
//   final String apiUrl = 'https://fir-10bba.firebaseio.com/products.json';
//
//   Future<List<Product>> fetchProducts() async {
//     final response = await http.get(Uri.parse(apiUrl));
//
//     if (response.statusCode == 200) {
//       List jsonResponse = json.decode(response.body);
//       return jsonResponse.map((product) => Product.fromJson(product)).toList();
//     } else {
//       throw Exception('Failed to load products');
//     }
//   }
// }

class ProductService {
  final FirebaseFirestore _db = FirebaseFirestore.instance;

  Future<List<Product>> fetchProducts() async {
    try {
      final snapshot = await _db.collection('productapp').get();
      return snapshot.docs.map((doc) => Product.fromJson(doc.data())).toList();
    } catch (e) {
      throw Exception('Failed to load products');
    }
  }
}

