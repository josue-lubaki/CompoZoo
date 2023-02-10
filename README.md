# CompoZoo
Course on MVI architecture in the context of an android application (Part 2)

CompoZoo is a Kotlin (Jetpack Compose) based application that displays a list of animals. It utilizes Retrofit to fetch data on animals and employs an MVI architecture to ensure a consistent and reliable user experience. This app offers a straightforward and intuitive experience for exploring and discovering the diverse animals of the world

## Demo Application
[![Demo](https://videoapi-muybridge.vimeocdn.com/animated-thumbnails/image/59f697a0-4e60-4695-83f8-65418fff177d.gif?ClientID=vimeo-core-prod&Date=1675991804&Signature=16ae8fdd1f0671a253c8dc0c970e67d54acdb08e)](https://player.vimeo.com/video/797552896)



## Screenshots

<table border="1">
    <tr>
        <th align="center"> LIGHT MODE </th>
        <th align="center"> DARK MODE </th>
    </tr>
    <tr>
        <td align="center"> <img src="https://i.imgur.com/Fjv3B7y.png" width=360 height=760 /></td>
        <td align="center"> <img src="https://i.imgur.com/T6R000x.png" width=360 height=760 /></td>
    </tr>
    
</table>

## Installation
1. Clone this repository and import into **Android Studio**

```bash
git clone https://github.com/josue-lubaki/CompoZoo.git
```

2. Run the app

```bash
Run the app (Run > Run 'app')
```

## Dependencies
- Retrofit
- Coil
- Dagger Hilt
- Kotlin Coroutines
- Jetpack Compose

## Architecture
CompoZoo is based on the MVI architecture. The MVI architecture is a unidirectional data flow architecture that is based on the principles of reactive programming. It is a great fit for Android development because it is easy to understand, test, and maintain. It also provides a consistent and reliable user experience.
<img src="https://i.imgur.com/53BsOd1.png" width="640" height="420" />

## License
[MIT License](https://github.com/josue-lubaki/CompoZoo/blob/main/licence)

```
Copyright (c) 2023 Josue Lubaki

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
