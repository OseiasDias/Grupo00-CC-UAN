import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: ConsultaSaldoPage(),
    );
  }
}

class ConsultaSaldoPage extends StatefulWidget {
  @override
  _ConsultaSaldoPageState createState() => _ConsultaSaldoPageState();
}

class _ConsultaSaldoPageState extends State<ConsultaSaldoPage> {
  void _mostrarSaldo() {
    showModalBottomSheet(
      context: context,
      builder: (BuildContext context) {
        return Container(
          height: 200,
          color: Colors.white,
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text(
                  "Saldo Atual",
                  style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                ),
                SizedBox(height: 20),
                Text(
                  "R\$ 1.234,56", // Substitua com o valor real do saldo
                  style: TextStyle(fontSize: 32, fontWeight: FontWeight.bold),
                ),
                SizedBox(height: 20),
                ElevatedButton(
                  onPressed: () => Navigator.pop(context),
                  child: Text("Fechar"),
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //backgroundColor: Colors.blue, // Removed for simplicity
      body: Stack(
        children: <Widget>[
          // Imagem de Fundo
          Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                image: AssetImage(
                    'assets/AdobeStock_660111750_Preview.jpeg'), // Substitua com o caminho da sua imagem
                fit: BoxFit.cover,
              ),
            ),
          ),

          // Conteúdo da Página - Centralizado
          Center(
            // Wrap the Column in a Center widget
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center, // Center vertically
              crossAxisAlignment:
                  CrossAxisAlignment.center, // Center horizontally
              children: <Widget>[
                // Título
                Text(
                  "Consultando Saldo",
                  style: TextStyle(fontSize: 36, color: Colors.white),
                ),

                // Espaçamento
                SizedBox(height: 40),

                // Botão para Abrir Modal (toque na tela ou outro gatilho)
                GestureDetector(
                  onTap: _mostrarSaldo,
                  child: Container(
                    padding: EdgeInsets.all(20),
                    decoration: BoxDecoration(
                      color: Colors.orange[200],
                      borderRadius: BorderRadius.circular(10),
                    ),
                    child: Text(
                      "Consultar Saldo",
                      style: TextStyle(fontSize: 20),
                    ),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
