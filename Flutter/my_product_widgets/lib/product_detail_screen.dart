import 'package:flutter/material.dart';
import 'product.dart';

class ProductDetailScreen extends StatelessWidget{
  final Product product;
  ProductDetailScreen({required this.product});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(product.name),),

    );

  }
}