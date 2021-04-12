#language: pt
#encoding: iso-8859-1
Funcionalidade: Restricoes

  @ProvaTecnicaAPIMaster @Restricoes
  Esquema do Cenario: Consulta se o CPF <CPF> possui ou nao restricao
    Entao realiza a consulta se o CPF "<CPF>" possui ou nao restricao
    Exemplos:
    |CPF          |
    | 97093236014 |
    | 84809766080 |
    | 60094146012 |
    | 62648716050 |
    | 26276298085 |
    | 01317496094 |
    | 55856777050 |
    | 19626829001 |


  @ProvaTecnicaAPIMaster @Restricoes
  Esquema do Cenario: Consulta se o CPF invalido <CPF> possui ou nao restricao
    Entao realiza a consulta se o CPF invalido "<CPF>" possui ou nao restricao
    Exemplos:
      |CPF          |
      | TESTE       |
      | 99999999999 |
      | 0           |