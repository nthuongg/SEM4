import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/order.dart';
import 'package:cloud_firestore/cloud_firestore.dart';


class ProductService {
  final FirebaseFirestore _db = FirebaseFirestore.instance;

  Future<List<Order>> fetchProducts() async {
    try {
      final snapshot = await _db.collection('orderapp').get();
      return snapshot.docs.map((doc) => Order.fromJson(doc.data())).toList();
    } catch (e) {
      throw Exception('Failed to load orders');
    }
  }
}