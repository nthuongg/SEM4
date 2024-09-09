import 'package:flutter/material.dart';
import '../models/order.dart';
import '../services/firebase_service.dart'; // Ensure this path is correct

class AddOrderPage extends StatefulWidget {
  final Function(Orderapp) onAddOrder;

  AddOrderPage({required this.onAddOrder});

  @override
  _AddOrderPageState createState() => _AddOrderPageState();
}

class _AddOrderPageState extends State<AddOrderPage> {
  final OrderService _orderService = OrderService();
  final TextEditingController _idController = TextEditingController();
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _noteController = TextEditingController();
  final TextEditingController _quantityController = TextEditingController();

  void _addItem() async {
    final int id = int.tryParse(_idController.text) ?? 0;
    final name = _nameController.text.trim();
    final note = _noteController.text.trim();
    final quantity = int.tryParse(_quantityController.text.trim()) ?? 1;

    if (name.isNotEmpty) {
      final newOrder = Orderapp(
        id: id, // Generate ID locally
        name: name,
        note: note,
        quantity: quantity,
      );

      try {
        await _orderService.addOrder(newOrder); // Add order to Firestore
        widget.onAddOrder(newOrder); // Callback to update the UI
        Navigator.pop(context); // Close Add Order Page
      } catch (e) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Failed to add order: ${e.toString()}')),
        );
      }
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Please enter a dish name.')),
      );
    }
  }

  void _resetFields() {
    _idController.clear();
    _nameController.clear();
    _noteController.clear();
    _quantityController.clear();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Add New Order'),
        backgroundColor: Color(0xFF4B6E4B), // Using the custom theme color
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _idController,
              decoration: InputDecoration(
                labelText: 'ID',
                border: OutlineInputBorder(),
              ),
              keyboardType: TextInputType.number,
            ),
            SizedBox(height: 10),
            TextField(
              controller: _nameController,
              decoration: InputDecoration(
                labelText: 'Dish Name',
                border: OutlineInputBorder(),
              ),
            ),
            SizedBox(height: 10),
            TextField(
              controller: _noteController,
              decoration: InputDecoration(
                labelText: 'Notes',
                border: OutlineInputBorder(),
              ),
            ),
            SizedBox(height: 10),
            TextField(
              controller: _quantityController,
              decoration: InputDecoration(
                labelText: 'Quantity',
                border: OutlineInputBorder(),
              ),
              keyboardType: TextInputType.number,
            ),
            SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                ElevatedButton(
                  onPressed: _addItem,
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Color(0xFF4B6E4B),
                  ),
                  child: Text('Add Item'),
                ),
                OutlinedButton(
                  onPressed: _resetFields,
                  child: Text('Reset'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
