import 'package:flutter/material.dart';
import 'package:flutter_application_1/main.dart';
import 'estacao.dart';
import 'consultasaldo.dart';
import 'package:flutter_application_1/consultasaldo.dart';
import 'consultestacao.dart';
import 'compartilhardados.dart';

class Berento extends StatelessWidget {
  const Berento({super.key});
  void mostrarModalAtivacao(BuildContext context) {
    showModalBottomSheet(
      context: context,
      builder: (context) => Container(
        height: 200,
        color: Colors.white,
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                'Utilizador Ativo',
                style: TextStyle(
                  color: Colors.green,
                  fontSize: 20.0,
                  fontWeight: FontWeight.bold,
                ),
              ),
              SizedBox(height: 10.0),
              ElevatedButton(
                onPressed: () {
                  // Simular a desativação do usuário (substituir pela lógica real)
                  Navigator.pop(context);
                  ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(
                      content: Text(
                        'Utilizador Desativado',
                        style: TextStyle(color: Colors.white),
                      ),
                      backgroundColor: Colors.redAccent,
                    ),
                  );
                },
                child: Text(
                  'Desativar Utilizador',
                  style: TextStyle(color: Colors.white),
                ),
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.red,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // Set a transparent background color for the Scaffold
      backgroundColor: Colors.transparent,
      body: Stack(
        // Use a Stack widget for layering
        children: [
          // Container for the background image
          Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                image: AssetImage(
                    'assets/AdobeStock_660111750_Preview.jpeg'), // Replace with your image path
                fit: BoxFit
                    .cover, // Adjust the fit as needed (cover, contain, etc.)
              ),
            ),
          ),
          Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center, // Centralize buttons
              children: [
                // Your existing button with "Consultar Saldo" text
                ElevatedButton(
                  onPressed: () {
                    // Navegue para a página Estação
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => ConsultaSaldoPage()),
                    );
                  },
                  child: Text(
                    "Consultar Saldo",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.orange[200]!,
                    minimumSize: Size(1000, 50),
                  ),
                ),
                SizedBox(height: 20.0), // Adiciona espaçamento entre os botões
                ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => ConsultaEstacaoPage()),
                    );
                  },
                  child: Text(
                    "Consultar Estação",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  style: ElevatedButton.styleFrom(
                    backgroundColor:
                        Colors.orange[200]!, // Define preto para o botão 5
                    minimumSize:
                        Size(1000, 50), // Define largura maior para o botão 5
                  ),
                ),
                SizedBox(height: 20.0),
                ElevatedButton(
                  onPressed: () {
                    mostrarModalAtivacao(context);
                  },
                  child: Text(
                    "Ativar Utilizador",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.orange[200]!,
                    minimumSize: Size(1000, 50),
                  ),
                ),

                SizedBox(height: 20.0),
                ElevatedButton(
                  onPressed: () {
                    // Navegue para a página Estação
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => EstacaoPage()),
                    );
                  },
                  child: Text(
                    "Lista De Estaçoes",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.orange[200]!,
                    minimumSize: Size(1000, 50),
                  ),
                ),

                SizedBox(height: 20.0),
                ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => CompartilharDados()),
                    );
                  },
                  child: Text(
                    "Trocar Dados",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.orange[200]!,
                    minimumSize: Size(1000, 50),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        currentIndex: 0,
        backgroundColor: Colors.orange[200]!,
        fixedColor: Colors.white,
        unselectedItemColor: Colors.grey,
        items: [
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Página Inicial',
          ),
          BottomNavigationBarItem(icon: Icon(Icons.search), label: 'Pesquisa'),
          BottomNavigationBarItem(icon: Icon(Icons.person), label: 'Perfil'),
          BottomNavigationBarItem(icon: Icon(Icons.help), label: 'Ajuda'),
        ],
      ),
    );
  }
}
