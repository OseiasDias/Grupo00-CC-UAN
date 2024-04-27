import 'package:flutter/material.dart';

class CompartilharDados extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Compartilhar Pontos'),
        backgroundColor: Colors.orange[200]!,
      ),
      body: Stack(
        children: [
          // Container da imagem de fundo
          Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                image: AssetImage('assets/AdobeStock_660111750_Preview.jpeg'),
                fit: BoxFit.cover,
              ),
            ),
          ),
          // Container centralizado para conteúdo
          Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                // Texto "Compartilhar Pontos"
                Text(
                  'Compartilhar Pontos',
                  style: TextStyle(
                    fontSize: 24.0,
                    fontWeight: FontWeight.bold,
                    color: Colors.white,
                  ),
                ),
                SizedBox(height: 20.0),
                // Formulário com campos Nome e Pontos
                Form(
                  child: Column(
                    children: [
                      TextFormField(
                        decoration: InputDecoration(
                          labelText: 'Nome do Destinatário',
                          border: OutlineInputBorder(),
                        ),
                      ),
                      SizedBox(height: 10.0),
                      TextFormField(
                        decoration: InputDecoration(
                          labelText: 'Pontos',
                          border: OutlineInputBorder(),
                        ),
                        keyboardType:
                            TextInputType.number, // Para entrada numérica
                      ),
                    ],
                  ),
                ),
                SizedBox(height: 20.0),
                // Botão verde "Enviar"
                ElevatedButton(
                  onPressed: () {
                    // Manipular envio do formulário (lógica de envio de pontos)
                    print('Enviar pontos');
                  },
                  child: Text(
                    'Enviar',
                    style: TextStyle(color: Colors.white),
                  ),
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.green,
                    minimumSize: Size(300, 50),
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
