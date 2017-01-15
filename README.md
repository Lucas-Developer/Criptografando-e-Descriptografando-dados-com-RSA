O RSA é o método de criptografia mais utilizado no mundo. No RSA utilizamos duas chaves, uma chave para encriptação e outra para decriptação. Ele resolve o problema de distribuição de chaves da criptografia simétrica usando envelopamento digital e a segurança é baseada na fatoração de números extensos. Quanto maior a chave maior a segurança, porém o processamento também é maior.

A construção de chaves é feita através da multiplicação de dois números primos relativamente grandes que gera um número que será elevado a um expoente que é um número público, e após isso ele é novamente elevado a outro expoente que é um número privado. Assim teremos um número público e um número privado. O processo de descriptografia (em que os números primos são novamente gerados) será revertido através de fatoração, que é o inverso da multiplicação.

O RSA foi construído sobre uma das áreas mais clássicas da matemática, a teoria dos números. Ele se baseia na dificuldade em fatorar um número em seus componentes primos (números divisíveis por 1 e por ele mesmo). Todo número inteiro positivo maior que 1 pode ser decomposto de forma única em um produto de números primos, por exemplo, 26 é um produto de 2 * 13, 44 é um produto de 2 * 2 * 11. Apesar de parecer simples fatorar esses números pequenos, a situação fica bastante complexa e demorada quando temos que fatorar números grandes não podendo ser resolvidos em um tempo polinomial determinístico. No RSA a chave pública e a chave privada são geradas com base na multiplicação de dois números primos e o resultado desta multiplicação será público, mas se o número for grande o suficiente, fatorar este número para descobrir os primos que multiplicamos para formá-lo pode demorar anos. Por isso que o RSA é seguro, sendo impossível quebrar a sua criptografia.

O método do RSA se dá primeiramente com a construção de uma tabela atribuindo a cada letra um número. Isto é necessário visto que o RSA codifica somente números. Após isso, escolhemos os números primos e quanto maior for esses números melhor. A RSA Data Security, que é responsável pela padronização do RSA, recomenda que se utilizem chaves de 2048 bits (o que resulta em um número com 617 dígitos) se quisermos garantir que a chave não seja quebrada até pelo menos o ano de 2030. Após a atribuição de números para as letras agora temos que calcular a função “totiente” que diz a quantidade de co-primos de um numero que são menores que ele mesmo. Feito isso, o próximo passo é calcular a chave pública que é onde escolhe-se um número "e" em que 1 < e < função totiente. Com a chave pública em mãos podemos agora cifrar a mensagem aplicando, para cada letra, a fórmula “c = m ^ e mod n”, onde "e" é a chave pública e "m" é o valor numérico da letra.

Para exemplificar o funcionamento do algoritmo vamos escolher inicialmente dois números primos quaisquer “P” e “Q”. Obviamente que vamos escolher dois números primos pequenos apenas por questão de exemplificação. Portanto, consideramos que “P = 17” e “Q = 11”.

Após definirmos os valores dos números primos devemos calcular dois novos números N e Z de acordo com os números P e Q escolhidos, portanto temos que:

N = P * Q

Z = (P-1)*(Q-1)

Assim, após substituir os valores teremos como resultado:

N = 17 * 11 = 187

Z = 16 * 10 = 160

O próximo passo é definir um número D que tenha a propriedade de ser primo em relação a Z. Podemos escolher qualquer número como, por exemplo, o número “7”.

Agora podemos começar o processo de criação da chave pública e da chave privada. Devemos encontrar um número E que satisfaça a propriedade "(E * D) mod Z = 1". Se tomarmos o número “1” e substituindo os valores na fórmula teremos "E = 1 => (1 * 7) mod 160 = 7" que não satisfaz a condição, pois o resultado foi “7”. Se tomarmos os números “2”, “3” até o “22” nenhum satisfará a condição, mas o número “23” satisfará resultando em "E = 23 => (23 * 7) mod 160 = 1". Outros números também satisfazem essa condição, como “183”, “343”, “503”, etc.

Dessa forma, tomamos como referência “E = 23”. Agora podemos definir as chaves de encriptação e desencriptação. Para criptografar utilizamos “E” e “N”, esse par de números é utilizado como chave pública. Para descriptografar utilizamos “D” e “N”, esse par de números é utilizado como chave privada.

Assim, temos as equações definidas abaixo:

Texto Criptografado = (Texto Puro ^ E) mod N

Texto Puro = (texto Criptografado ^ D) mod N

Como um exemplo prático vamos imaginar uma mensagem bastante simples que tem o número “4” no seu corpo e será retornada ao destinatário. Para criptografar essa mensagem teríamos o texto original como sendo “4”, o texto criptografado seria "(4 ^ 23) mod 187" que é "70.368.744.177.664 mod 187" resultando em “64”.

Para descriptografar destinatário recebe o texto “64”. Recebido o texto criptografado e aplicando a fórmula temos que o texto original será "(64 ^ 7) mod 187" ou "4.398.046.511.104 mod 187" que resulta em “4” que é o texto originalmente criado. Como o RSA trabalha com número devemos converter um alfabeto em número, assim a letra A seria 0, B seria 1, C seria 2, e assim por diante.

Podemos notar que a escolha dos números primos envolvidos é fundamental para o algoritmo, por isso escolhe-se números primos gigantes para garantir que a chave seja inquebrável.
