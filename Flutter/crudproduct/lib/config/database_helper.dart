import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';
import 'dart:io';
import 'package:flutter/services.dart' show rootBundle;

class DatabaseHelper {
  static final DatabaseHelper _instance = DatabaseHelper._internal();
  factory DatabaseHelper() => _instance;
  DatabaseHelper._internal();

  static Database? _database;

  Future<Database> get database async {
    if (_database != null) return _database!;
    _database = await _initDatabase();
    return _database!;
  }

  Future<Database> _initDatabase() async {
    final directory = await getApplicationDocumentsDirectory();
    final path = join(directory.path, 'databasebackup.db');

    final file = File(path);
    if (!await file.exists()) {
      await _copyDatabaseFromAssets(path);
    }

    final db = await openDatabase(
      path,
      version: 1,
      onCreate: _onCreate,
      onUpgrade: _onUpgrade,
    );

    // Dọn dẹp dữ liệu cũ trong SQLite
    await _clearDatabase(db);

    // Tải và lưu dữ liệu mới từ Firebase
    await _fetchAndInsertDataFromFirebase(db);

    return db;
  }

  Future<void> _copyDatabaseFromAssets(String path) async {
    final data = await rootBundle.load('assets/databasebackup.db');
    final bytes = data.buffer.asUint8List();
    await File(path).writeAsBytes(bytes);
  }

  Future<void> _onCreate(Database db, int version) async {
    await db.execute('''
      CREATE TABLE IF NOT EXISTS cart_items (
        id INTEGER PRIMARY KEY,
        productId INTEGER,
        quantity INTEGER
      )
    ''');

    await db.execute('''
      CREATE TABLE IF NOT EXISTS products (
        id INTEGER PRIMARY KEY,
        name TEXT,
        description TEXT,
        price INTEGER
      )
    ''');
  }

  Future<void> _onUpgrade(Database db, int oldVersion, int newVersion) async {
    if (oldVersion < newVersion) {
      // Update logic
    }
  }

  Future<void> _clearDatabase(Database db) async {
    // Xóa tất cả dữ liệu từ bảng 'products' và 'cart_items'
    await db.delete('products');
    await db.delete('cart_items');
    print('Dữ liệu cũ đã bị xóa.');
  }

  Future<void> _fetchAndInsertDataFromFirebase(Database db) async {
    FirebaseFirestore firestore = FirebaseFirestore.instance;

    // Lấy dữ liệu từ Firestore
    QuerySnapshot querySnapshot = await firestore.collection('products').get();

    // Lưu dữ liệu vào SQLite
    for (var doc in querySnapshot.docs) {
      Map<String, dynamic> data = doc.data() as Map<String, dynamic>;
      await db.insert('products', {
        'id': data['id'],
        'name': data['name'],
        'description': data['description'],
        'price': data['price'],
      });
    }
    print('Dữ liệu đã được tải từ Firebase và lưu vào SQLite.');
  }
}


class FirebaseConfig {
  static FirebaseOptions get firebaseOptions {
    return const FirebaseOptions(
      apiKey: "AIzaSyA4XuezxuBu4yCwg_3-ZKbxw8efYgidq2Q",
      authDomain: "flutter-test1-81df5.firebaseapp.com",
      projectId: "flutter-test1-81df5",
      storageBucket: "flutter-test1-81df5.appspot.com",
      messagingSenderId: "870979889957",
      appId: "1:870979889957:web:75d8aa966194ed3fe5c8cc",
    );
  }
}