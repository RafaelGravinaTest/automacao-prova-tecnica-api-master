package model;

public class Simulacoes {
    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private Double valor;
    private Integer parcelas;
    private Boolean seguro;

    Simulacoes(Integer id, String nome, String cpf, String email, Double valor, Integer parcelas, Boolean seguro) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.valor = valor;
        this.parcelas = parcelas;
        this.seguro = seguro;
    }

    Simulacoes() {}

    public Integer getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public Double getValor() {
        return this.valor;
    }

    public Integer getParcelas() {
        return this.parcelas;
    }

    public Boolean getSeguro() {
        return this.seguro;
    }


    public String asJSON() {
        return "{\"nome\": \"" + this.nome + "\", \"cpf\": " + this.cpf + ", \"email\": \"" + this.email + "\", \"valor\": " + this.valor + ", \"parcelas\": " + this.parcelas + ", \"seguro\": " + this.seguro + "}";
    }

    public static Simulacoes.SimulacoesBuilder builder() {
        return new Simulacoes.SimulacoesBuilder();
    }

    public static class SimulacoesBuilder {
        private Integer id;
        private String nome;
        private String cpf;
        private String email;
        private Double valor;
        private Integer parcelas;
        private Boolean seguro;

        SimulacoesBuilder() {
        }

        public Simulacoes.SimulacoesBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public Simulacoes.SimulacoesBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Simulacoes.SimulacoesBuilder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Simulacoes.SimulacoesBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Simulacoes.SimulacoesBuilder valor(Double valor) {
            this.valor = valor;
            return this;
        }

        public Simulacoes.SimulacoesBuilder parcelas(Integer parcelas) {
            this.parcelas = parcelas;
            return this;
        }

        public Simulacoes.SimulacoesBuilder seguro(Boolean seguro) {
            this.seguro = seguro;
            return this;
        }

        public Simulacoes build() {
            return new Simulacoes(this.id, this.nome, this.cpf, this.email, this.valor, this.parcelas, this.seguro);
        }

        public String toString() {
            return "{id=" + this.id + ", nome=" + this.nome + ", cpf=" + this.cpf + ", email=" + this.email + ", valor=" + this.valor + ", parcelas=" + this.parcelas + ", seguro=" + this.seguro + "}";
        }
    }
}
