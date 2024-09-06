import 'package:crudproduct/pages/cart_page.dart';
import 'package:crudproduct/pages/product_page.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:crudproduct/services/cart_service.dart';

import 'config/database_helper.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  // Đảm bảo chỉ khởi tạo Firebase một lần
  try {
    await Firebase.initializeApp(options: FirebaseConfig.firebaseOptions);
  } catch (e) {
    print('Firebase initialization error: $e');
  }
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  final CartService cartService = CartService();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Product App',
      initialRoute: '/',
      routes: {
        '/': (context) => ProductPage(cartService: cartService), // Pass cartService to ProductPage
        '/cart': (context) => CartPage(cartService: cartService),
      },
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
    );
  }
}
