import 'package:flutter/material.dart';
import 'infoestacao.dart'; // Importe a classe InfoEstacao

class EstacaoPage extends StatefulWidget {
  const EstacaoPage({super.key});

  @override
  State<EstacaoPage> createState() => _EstacaoPageState();
}

class _EstacaoPageState extends State<EstacaoPage> {
  int? _selectedStationIndex; // Variável para controlar a estação selecionada

  // Defina alguns dados fictícios para as estações (substitua pela sua fonte de dados real)
  final List<Map<String, dynamic>> _estacoes = [
    {
      'nomeEstacao': 'Estação Central',
      'localizacaoEstacao': 'Avenida Paulista, São Paulo',
      'bicicletasLivres': 5,
      'docasLivres': 3,
      'imagePath':
          'assets/AdobeStock_660111750_Preview.jpeg', // Substitua pelo caminho da sua imagem
    },
    {
      'nomeEstacao': 'Estação Ibirapuera',
      'localizacaoEstacao': 'Parque Ibirapuera, São Paulo',
      'bicicletasLivres': 2,
      'docasLivres': 8,
      'imagePath':
          'assets/AdobeStock_660111750_Preview.jpeg', // Substitua pelo caminho da sua imagem
    },
    // Adicione mais entradas de estações aqui seguindo o mesmo formato
    {
      'nomeEstacao': 'Estação Pinheiros',
      'localizacaoEstacao': 'Parque Estadual Villa-Lobos, São Paulo',
      'bicicletasLivres': 10,
      'docasLivres': 0,
      'imagePath':
          'assets/AdobeStock_660111750_Preview.jpeg', // Substitua pelo caminho da sua imagem
    },
    {
      'nomeEstacao': 'Estação Anhangabaú',
      'localizacaoEstacao': 'Vale do Anhangabaú, São Paulo',
      'bicicletasLivres': 7,
      'docasLivres': 1,
      'imagePath':
          'assets/AdobeStock_660111750_Preview.jpeg', // Substitua pelo caminho da sua imagem
    },
    {
      'nomeEstacao': 'Estação Rangel',
      'localizacaoEstacao': 'Avenida Brasil',
      'bicicletasLivres': 7,
      'docasLivres': 1,
      'imagePath':
          'assets/AdobeStock_660111750_Preview.jpeg', // Substitua pelo caminho da sua imagem
    },
    {
      'nomeEstacao': 'Estação Samba',
      'localizacaoEstacao': 'Luanda',
      'bicicletasLivres': 7,
      'docasLivres': 1,
      'imagePath':
          'assets/AdobeStock_660111750_Preview.jpeg', // Substitua pelo caminho da sua imagem
    },
    {
      'nomeEstacao': 'Estação Ilha',
      'localizacaoEstacao': 'Luanda',
      'bicicletasLivres': 7,
      'docasLivres': 1,
      'imagePath':
          'assets/AdobeStock_660111750_Preview.jpeg', // Substitua pelo caminho da sua imagem
    },
    {
      'nomeEstacao': 'Estação Shoprite',
      'localizacaoEstacao': 'Kilamba Kiaxi',
      'bicicletasLivres': 7,
      'docasLivres': 1,
      'imagePath':
          'assets/AdobeStock_660111750_Preview.jpeg', // Substitua pelo caminho da sua imagem
    },
    {
      'nomeEstacao': 'Estação Grafanil',
      'localizacaoEstacao': 'Viana-KM9',
      'bicicletasLivres': 7,
      'docasLivres': 1,
      'imagePath':
          'assets/AdobeStock_660111750_Preview.jpeg', // Substitua pelo caminho da sua imagem
    },
    {
      'nomeEstacao': 'Estação Kilamba',
      'localizacaoEstacao': 'Golf2, Kilamba Kiaxi',
      'bicicletasLivres': 7,
      'docasLivres': 1,
      'imagePath':
          'assets/AdobeStock_660111750_Preview.jpeg', // Substitua pelo caminho da sua imagem
    },
    // ... e assim por diante
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text('Lista de Estações'),
        backgroundColor: Colors.orange[200]!,
        automaticallyImplyLeading: false, // Remover botão de voltar
      ),
      body: ListView.builder(
        itemCount: _estacoes.length, // Use o tamanho dos dados das estações
        itemBuilder: (context, index) {
          final estacao = _estacoes[index]; // Acesse os dados da estação

          final isSelected = index == _selectedStationIndex;

          return ListTile(
            tileColor: isSelected ? Colors.orange[200] : Colors.grey.shade400,
            contentPadding: const EdgeInsets.all(6),
            leading: const Icon(Icons.home),
            title: Text(
                "Estação ${estacao['nomeEstacao']}"), // Use o nome da estação
            onTap: () {
              setState(() {
                _selectedStationIndex = index;
              });

              // Navegue para a página InfoEstacao e passe os dados da estação
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => InfoEstacao(
                    nomeEstacao: estacao['nomeEstacao'],
                    localizacaoEstacao: estacao['localizacaoEstacao'],
                    bicicletasLivres: estacao['bicicletasLivres'],
                    docasLivres: estacao['docasLivres'],
                    imagePath: estacao['imagePath'],
                  ),
                ),
              );
            },
            trailing: Visibility(
              visible: isSelected,
              child: Row(
                mainAxisSize: MainAxisSize.min, // Layout compacto dos botões
                children: [
                  // ... código existente do botão para "levantar bicicleta"
                  const SizedBox(width: 10),
                  // ... código existente do botão para "devolver bicicleta"
                ],
              ),
            ),
          );
        },
      ),
    );
  }
}
