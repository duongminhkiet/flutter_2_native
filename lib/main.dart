import 'dart:developer';

import 'package:flutter/material.dart';

import 'package:flutter/services.dart';

void main() => runApp(MyApp());

const CHANNEL = "in.zmk.com.flutter_2_native_in.channel";
const KEY_NATIVE = "showNativeView";

class MyApp extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();

//  @override
//  _MyHomePageState createState() => _MyHomePageState();
}

class _HomeScreenState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform = const MethodChannel(CHANNEL);
  String title = 'the first';

  @override
  void initState() {
    super.initState();
    platform.setMethodCallHandler(_handleMethod);
  }

  Future<dynamic> _handleMethod(MethodCall call) async {
    setState(() {
      title = 'the second';
    });
    log('xxxxxxxxxxxxxx======xxxxxxxxxxxxxx');
    debugPrint(call.arguments + '-------->>>>>>OKOKOK');
    switch (call.method) {
      case "message":
        debugPrint(call.arguments);
        setState(() {
          title += 'the second' + call.arguments;
        });
        return new Future.value("");
      default:
        setState(() {
          title += 'the second' + call.arguments;
        });
        debugPrint(call.arguments + '-------->>>>>>OKOKOK');
    }
  }

  Future<Null> _showNativeView() async {
    title = await platform.invokeMethod(KEY_NATIVE,{"text_2_native":"this is text from flutter to native"});
    setState(() {
      title += 'the second xx';
    });
  }

  int _counter = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'You have pushed the button this many times:' + title,
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.display1,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _showNativeView,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}
