import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/order.dart';
import 'package:cloud_firestore/cloud_firestore.dart';


class OrderService {
  final FirebaseFirestore _db = FirebaseFirestore.instance;

  Future<List<Orderapp>> fetchOrders() async {
    try {
      final snapshot = await _db.collection('orderapp').get();
      return snapshot.docs.map((doc) => Orderapp.fromJson(doc.data())).toList();
    } catch (e) {
      throw Exception('Failed to load orders');
    }
  }

  Future<void> addOrder(Orderapp order) async {
    try {
      await _db.collection('orderapp').add(order.toJson());
    } catch (e) {
      throw Exception('Failed to add order');
    }
  }
}