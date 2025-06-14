import "./registerPatient.css";
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer, toast } from 'react-toastify';

import paciente from '../../assets/paciente.svg';
import { useState } from 'react';
import api from '../../services/axiosConfig';
import { useNavigate } from 'react-router-dom';

export default function RegisterPatient() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    login: '',
    senha: '',
    nome: '',
    tel: '',
    email: '',
    dataNasc: '',
  });

  const handleChange = (e) => {
    setFormData(prev => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post('/paciente/create', formData);
      toast.success("Paciente cadastrado com sucesso!");

      setTimeout(() => {
        navigate("/api/login"); // redireciona após sucesso
      }, 2000);
    } catch (err) {
      if (err.response && err.response.data) {
        toast.error(`Erro: ${err.response.data.message || "Erro ao cadastrar paciente."}`);
      } else {
        toast.error("Erro inesperado.");
      }
    }
  };

  return (
    <>
      <main>
        <form onSubmit={handleSubmit}>
          <h2>Cadastro de paciente</h2>

          <label htmlFor='login'>Login</label>
          <input name='login' type='text' required id="login" placeholder="Username aqui..." onChange={handleChange} />

          <label htmlFor='senha'>Senha</label>
          <input name='senha' type='password' required id="senha" placeholder="Senha..." onChange={handleChange} />

          <label htmlFor='nome'>Nome</label>
          <input name='nome' type='text' required id="nome" placeholder="Digite o nome aqui..." onChange={handleChange} />

          <label htmlFor='tel'>Telefone</label>
          <input name='tel' type='tel' required id="tel" placeholder="(x) 11111-1111" onChange={handleChange} />

          <label htmlFor='email'>E-mail</label>
          <input name='email' type='email' required id="email" placeholder="email@gmail.com" onChange={handleChange} />

          <label htmlFor='dataNasc'>Data de nascimento</label>
          <input name='dataNasc' type='date' required id="dataNasc" onChange={handleChange} />

          <div className="form-button">
            <button className="button-primary" type="submit">Cadastre-se →</button>
          </div>

          <p style={{ marginTop: '1rem' }}>
            Já possui conta? <a href="/api/login">Entrar</a>
          </p>
        </form>

        <div className="paciente-content">
          <img src={paciente} alt="mulher branca de olho azul usando máscara" />
        </div>
      </main>
      <ToastContainer />
    </>
  );
}
