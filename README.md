# Ambiente Intelligence

# Trabalho de DSW

Para declarar o compomente você deve adicionar o arquivo [ambient_inteligence.tld](https://github.com/DaviRSSilva/ambientIntelligenceDSW/blob/master/WebContent/WEB-INF/ambient_inteligence.tld) na pasta de WEB-INF do seu projeto, assim como utilizar as classe JAVA na sua aplicação.

Para fazer uso do component em seu .jsp apenas adicione a seguinte tag:

```jsp
<%@ taglib prefix="ai" uri="WEB-INF/ambient_inteligence.tld"%>
```

E para utilizar o css que a tag retornará adicione a seguinte linha no head do seu html:
```html
...
<head>
  <ai:ambInt />
</head>
...
```
Os arquivos de css devem estar dentro da pasta WebContent na pasta css, e os nomes dos arquivos são os seguintes:
- css/stylesheet_lowLight.css
- css/stylesheet_insideAmbient.css
- css/stylesheet_externAmbient.css
- css/stylesheet_extremeLight.css

Caso não encontre os arquivos de estilo, em seu html aparecerá um comentário informando que não foi possível encontrar os arquivos.


E pronto! A partir de agora você terá .css's diferentes referentes a cada quantidade de lumens na sua sessão.

Lembrando que as alterações não são dinâmicas, o layout será alterado apenas na atualização da página. (A parte dinâmica fica para outro trabalho)
