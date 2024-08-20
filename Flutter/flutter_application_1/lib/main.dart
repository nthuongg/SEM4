import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  final List<String> menuItems = [
    'Play Game',
    'Settings',
    'Leaderboard',
    'Achievements',
    'Exit',
  ];

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Game Menu',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text('Game Menu'),
        ),
        body: MenuList(menuItems: menuItems),
      ),
    );
  }
}

class MenuList extends StatelessWidget {
  final List<String> menuItems;

  MenuList({required this.menuItems});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: menuItems.length,
      itemBuilder: (context, index) {
        return Card(
          child: ListTile(
            title: Text(menuItems[index]),
            onTap: () {
              print('${menuItems[index]} tapped');
            },
          ),
        );
      },
    );
  }
}