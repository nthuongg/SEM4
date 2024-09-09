import 'package:flutter/material.dart';

class Orderapp {
  final int id;
  final String name;
  final String note;
  final int quantity;

  Orderapp({
    required this.id,
    required this.name,
    required this.note,
    required this.quantity,
  });

  factory Orderapp.fromJson(Map<String, dynamic> json) {
    return Orderapp(
      id: json['id'] as int,
      name: json['name'] as String,
      note: json['note'] as String,
      quantity: json['quantity'] as int,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'note': note,
      'quantity': quantity,
    };
  }
}
