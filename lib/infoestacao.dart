import 'package:flutter/material.dart';
import 'package:async/async.dart';
import 'estacao.dart'; // Supondo que isso importe sua classe Estação
import 'package:google_maps_flutter/google_maps_flutter.dart';

class InfoEstacao extends StatefulWidget {
  final String nomeEstacao;
  final String localizacaoEstacao;
  final int bicicletasLivres;
  final int docasLivres;
  final String imagePath;

  const InfoEstacao({
    Key? key,
    required this.nomeEstacao,
    required this.localizacaoEstacao,
    required this.bicicletasLivres,
    required this.docasLivres,
    required this.imagePath,
  }) : super(key: key);

  @override
  _InfoEstacaoState createState() => _InfoEstacaoState();
}

class _InfoEstacaoState extends State<InfoEstacao> {
  LatLng?
      stationLocation; // Define uma variável para armazenar a localização da estação
  GoogleMapController? mapController;

  @override
  void initState() {
    super.initState();
    // Converte a string de localização em LatLng
    final latLng = LatLng(
      double.parse(widget.localizacaoEstacao.split(',')[0]),
      double.parse(widget.localizacaoEstacao.split(',')[1]),
    );
    stationLocation = latLng;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(Icons.home), // Use o ícone de casa
          ],
        ),
        backgroundColor:
            Colors.orange[200]!, // Defina a cor de fundo laranja claro
      ),
      body: Stack(
        children: [
          // Imagem de fundo
          Image.asset(
            widget.imagePath,
            fit: BoxFit.cover,
            height: double.infinity,
            width: double.infinity,
          ),

          // Container com informações da estação
          Container(
            margin: const EdgeInsets.all(20.0),
            padding: const EdgeInsets.all(16.0),
            decoration: BoxDecoration(
              color: Colors.white70,
              borderRadius: BorderRadius.circular(10.0),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  'Informação da Estação',
                  style: const TextStyle(
                    fontSize: 24.0,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(height: 16.0),
                Row(
                  children: [
                    const Icon(Icons.pedal_bike),
                    const SizedBox(width: 10.0),
                    Text('Bicicletas livres: ${widget.bicicletasLivres}'),
                  ],
                ),
                const SizedBox(height: 10.0),
                Row(
                  children: [
                    const Icon(Icons.dock),
                    const SizedBox(width: 10.0),
                    Text('Docas livres: ${widget.docasLivres}'),
                  ],
                ),
                const SizedBox(height: 10.0),
                Text('Nome da Estação: ${widget.nomeEstacao}'),
                const SizedBox(height: 10.0),
                Text('Localização: ${widget.localizacaoEstacao}'),
                const Spacer(), // Adicione Spacer para empurrar os botões para baixo
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ElevatedButton(
                      onPressed: () {
                        // Manuseie o pressionamento do botão "Levantar Bicicleta"
                      },
                      child: Text('Levantar Bicicleta'),
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.green, // Defina a cor verde
                      ),
                    ),
                    const SizedBox(width: 10.0),
                    ElevatedButton(
                      onPressed: () {
                        // Manuseie o pressionamento do botão "Devolver Bicicleta"
                      },
                      child: Text('Devolver Bicicleta'),
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.red, // Defina a cor
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),

          // Mapa com a localização da estação (opcional)
          // ...,
        ],
      ),
    );
  }
}
