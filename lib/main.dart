import 'package:flutter/material.dart';
import 'package:flutter_application_1/Berento.dart';

void main() => runApp(MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Homepage(),
    ));

class Homepage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [
              Colors.yellow[
                  200]!, // Adicione `!` para verificação explícita de nulo
              Colors.orange[200]!, // Ou qualquer outra cor desejada
            ],
          ),
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            SizedBox(
              height: 30,
            ),
            Padding(
              padding: EdgeInsets.all(20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(
                    "LOGIN",
                    style: TextStyle(color: Colors.white, fontSize: 40),
                  )
                ],
              ),
            ),
            Expanded(
                child: Container(
              decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(60),
                      topRight: Radius.circular(60))),
              child: Padding(
                padding: EdgeInsets.all(20),
                child: Column(
                  children: <Widget>[
                    SizedBox(
                      height: 40,
                    ),
                    Container(
                      padding: EdgeInsets.all(20),
                      decoration: BoxDecoration(
                          color: Colors.white,
                          borderRadius: BorderRadius.circular(10),
                          boxShadow: [
                            BoxShadow(
                                color: Color.fromRGBO(225, 95, 27, 3),
                                blurRadius: 20,
                                offset: Offset(0, 10))
                          ]),
                      child: Column(
                        children: <Widget>[
                          Container(
                            padding: EdgeInsets.all(10),
                            decoration: BoxDecoration(
                                border: Border(
                                    bottom: BorderSide(color: Colors.grey))),
                            child: TextField(
                                decoration: InputDecoration(
                              hintText: "Email",
                              hintStyle: TextStyle(color: Colors.grey),
                              border: InputBorder.none,
                            )),
                          ),
                          Container(
                            padding: EdgeInsets.all(10),
                            decoration: BoxDecoration(
                                border: Border(
                                    bottom: BorderSide(color: Colors.grey))),
                            child: TextField(
                                decoration: InputDecoration(
                              hintText: "Palavra Passe",
                              hintStyle: TextStyle(color: Colors.grey),
                              border: InputBorder.none,
                            )),
                          )
                        ],
                      ),
                    ),
                    SizedBox(
                      height: 40,
                    ),
                    Container(
                        height: 50,
                        margin: EdgeInsets.symmetric(horizontal: 90),
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(70),
                          color: Colors.orange,
                        ),
                        child: ElevatedButton(
                          onPressed: () {
                            // Defina a rota da outra página (substitua "HomePage" pelo nome correto)
                            final homepag = MaterialPageRoute(
                                builder: (context) => Berento());

                            // Navegue para a outra página
                            Navigator.push(context, homepag);
                          },
                          child: Text(
                            "Entrar",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          style: ElevatedButton.styleFrom(
                            backgroundColor: Colors.orange[200]!,
                            minimumSize: Size(800, 50),
                          ),
                        )),
                    SizedBox(
                      height: 40,
                    ),
                    Text(
                      "Esqueceste a tua palavra passe?",
                      style: TextStyle(color: Colors.grey),
                    ),
                  ],
                ),
              ),
            ))
          ],
        ),
      ),
    );
  }
}
