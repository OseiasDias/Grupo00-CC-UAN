import 'package:flutter/material.dart';
import '../infoestacao.dart'; // Importe a classe InfoEstacao
import 'package:flutter/material.dart';
import 'package:collection/collection.dart';

class ConsultaEstacaoPage extends StatefulWidget {
  const ConsultaEstacaoPage({super.key});

  @override
  _ConsultaEstacaoPageState createState() => _ConsultaEstacaoPageState();
}

class _ConsultaEstacaoPageState extends State<ConsultaEstacaoPage> {
  final TextEditingController _nomeEstacaoController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Consulta de Estação'),
        backgroundColor: Colors.orange[200]!, // Cor de fundo laranja claro
      ),
      body: Stack(
        children: [
          // Recipiente da imagem de fundo
          Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                image: AssetImage(
                    'assets/AdobeStock_660111750_Preview.jpeg'), // Substitua pelo caminho da sua imagem
                fit: BoxFit.cover,
              ),
            ),
          ),
          // Recipiente centralizado da barra de pesquisa
          Center(
            child: Container(
              padding: EdgeInsets.all(16.0), // Espaçamento interno
              decoration: BoxDecoration(
                color: Colors.white
                    .withOpacity(0.8), // Cor branca semi-transparente
                borderRadius:
                    BorderRadius.circular(10.0), // Bordas arredondadas
              ),
              child: Column(
                children: [
                  TextField(
                    controller: _nomeEstacaoController,
                    decoration: InputDecoration(
                      hintText: 'Buscar Estação', // Texto de sugestão
                      prefixIcon: Icon(Icons.search), // Ícone de pesquisa
                      border: OutlineInputBorder(
                        borderRadius:
                            BorderRadius.circular(10.0), // Bordas arredondadas
                      ),
                    ),
                  ),
                  SizedBox(height: 16.0), // Espaço vertical
                  ElevatedButton(
                    onPressed: () {
                      final nomeEstacao = _nomeEstacaoController.text.trim();

                      if (nomeEstacao.isNotEmpty) {
                        // Encontre os dados da estação correspondente usando firstWhereOrNull de dart:collection
                        final estacaoEncontrada = _estacoes.firstWhereOrNull(
                          (estacao) =>
                              estacao['nomeEstacao'].toLowerCase() ==
                              nomeEstacao.toLowerCase(),
                        );

                        if (estacaoEncontrada != null) {
                          // Navegue para a página InfoEstacao com os dados correspondentes
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => InfoEstacao(
                                nomeEstacao: estacaoEncontrada['nomeEstacao'],
                                localizacaoEstacao:
                                    estacaoEncontrada['localizacaoEstacao'],
                                bicicletasLivres:
                                    estacaoEncontrada['bicicletasLivres'],
                                docasLivres: estacaoEncontrada['docasLivres'],
                                imagePath: estacaoEncontrada['imagePath'],
                              ),
                            ),
                          );
                        } else {
                          // Exibe mensagem de erro caso nenhuma estação seja encontrada
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(
                              content:
                                  Text('Estação não encontrada: $nomeEstacao'),
                              backgroundColor: Colors.red,
                            ),
                          );
                        }
                      }
                    },
                    child: Text('Buscar'), // Texto do botão de busca
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  // Lista de dados de amostra de estações (substitua por sua fonte de dados real)
  final List<Map<String, dynamic>> _estacoes = [
    // ... (seus outros dados de estações)
    {
      "nomeEstacao": "Estação Central",
      "localizacaoEstacao": "Rua Central, 123",
      "bicicletasLivres": 10,
      "docasLivres": 5,
      "imagePath": "assets/AdobeStock_660111750_Preview.jpeg"
    },
    // ... (seus outros dados de estações)
  ];
}
