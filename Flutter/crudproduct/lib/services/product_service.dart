import 'package:crudproduct/config/database_helper.dart';
import 'package:crudproduct/models/product.dart';
import 'package:sqflite/sqflite.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class ProductService {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;

  Future<void> insertProduct(Product product) async {
    final db = await DatabaseHelper().database;

    // Thêm sản phẩm vào SQLite
    await db.insert(
      'products',
      product.toMap(),
      conflictAlgorithm: ConflictAlgorithm.replace,
    );

    // Thêm sản phẩm vào Firestore
    await _firestore.collection('products').doc(product.id.toString()).set(product.toMap());
  }

  Future<void> updateProduct(Product product) async {
    final db = await DatabaseHelper().database;

    // Cập nhật sản phẩm trong SQLite
    await db.update(
      'products',
      product.toMap(),
      where: 'id = ?',
      whereArgs: [product.id],
    );

    // Cập nhật sản phẩm trong Firestore
    await _firestore.collection('products').doc(product.id.toString()).update(product.toMap());
  }

  Future<void> deleteProduct(int id) async {
    final db = await DatabaseHelper().database;

    try {
      // Xóa sản phẩm khỏi SQLite
      await db.delete(
        'products',
        where: 'id = ?',
        whereArgs: [id],
      );
      print('Sản phẩm với ID $id đã được xóa khỏi SQLite.');

      // Xóa sản phẩm khỏi Firestore
      final docRef = _firestore.collection('products').doc(id.toString());
      final doc = await docRef.get();

      if (doc.exists) {
        await docRef.delete();
        print('Sản phẩm với ID $id đã được xóa khỏi Firestore.');
      } else {
        print('Sản phẩm với ID $id không tồn tại trong Firestore.');
      }
    } catch (e) {
      print('Lỗi khi xóa sản phẩm với ID $id: $e');
    }
  }
  Future<List<Product>> getProducts() async {
    final db = await DatabaseHelper().database;
    final List<Map<String, dynamic>> maps = await db.query('products');

    // Trả về danh sách sản phẩm từ SQLite
    return List.generate(maps.length, (i) {
      return Product.fromMap(maps[i]);
    });
  }

  Future<Product?> getProductById(int id) async {
    final db = await DatabaseHelper().database;
    final List<Map<String, dynamic>> maps = await db.query(
      'products',
      where: 'id = ?',
      whereArgs: [id],
    );

    if (maps.isNotEmpty) {
      return Product.fromMap(maps.first);
    } else {
      // Nếu không có sản phẩm trong SQLite, kiểm tra Firestore
      final doc = await _firestore.collection('products').doc(id.toString()).get();
      if (doc.exists) {
        final product = Product.fromMap(doc.data()!);
        // Cập nhật sản phẩm vào SQLite nếu chưa có
        await db.insert(
          'products',
          product.toMap(),
          conflictAlgorithm: ConflictAlgorithm.replace,
        );
        return product;
      } else {
        return null;
      }
    }
  }
}
