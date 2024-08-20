import 'package:flutter/material.dart';
import 'product.dart';
import 'product_data.dart';
import 'product_detail_screen.dart';

class ProductListScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Product List'),
      ),
      body: ListView.builder(
        itemCount: products.length,
        itemBuilder: (context, index) {
          final product = products[index];
          return Card(
            margin: EdgeInsets.all(8.0),
            child: ListTile(
              leading: Image.network(product.imageUrl, width: 50, height: 50, fit: BoxFit.cover),
              title: Text(product.name),
              subtitle: Text('\$${product.price.toStringAsFixed(2)}\n${product.description}'),
              isThreeLine: true,
              trailing: Icon(Icons.arrow_forward),
              // onTap: () {
              //   Navigator.push(
              //     context,
              //     MaterialPageRoute(
              //       builder: (context) => ProductDetailScreen(product: product),
              //     ),
              //   );
              // },
            ),
          );
        },
      ),
    );
  }
}