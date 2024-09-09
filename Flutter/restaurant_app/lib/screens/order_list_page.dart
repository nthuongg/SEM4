import 'package:flutter/material.dart';
import '../models/order.dart';
import '../services/firebase_service.dart';

class OrderListPage extends StatefulWidget {
  @override
  _OrderListPageState createState() => _OrderListPageState();
}

class _OrderListPageState extends State<OrderListPage> {
  late Future<List<Orderapp>> futureOrders;

  @override
  void initState() {
    super.initState();
    futureOrders = OrderService().fetchOrders(); // Fetch orders from OrderService
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Orders'),
        backgroundColor: Color(0xFF4B6E4B),
      ),
      body: FutureBuilder<List<Orderapp>>(
        future: futureOrders,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return ListView.builder(
              itemCount: snapshot.data?.length ?? 0,
              itemBuilder: (context, index) {
                var order = snapshot.data![index];
                return ListTile(
                  leading: Icon(Icons.fastfood, color: Colors.green),
                  title: Text(order.name),
                  subtitle: Text('Note: ${order.note}\nQuantity: ${order.quantity}'),
                  isThreeLine: true,
                );
              },
            );
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          }
          return Center(child: CircularProgressIndicator());
        },
      ),
    );
  }
}
