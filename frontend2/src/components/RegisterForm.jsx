// src/components/RegisterForm.jsx
import { useState } from 'react';
import { registerUser } from '../api/auth';

export default function RegisterForm() {
    const [form, setForm] = useState({
        firstName: '',
        lastName: '',
        gender: 'MALE',
        age: '',
        email: '',
        password: '',
    });
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await registerUser(form);
            setMessage(`Registered successfully! Token: ${response.data.token}`);
        } catch (error) {
            setMessage(error.response?.data?.message || 'Registration failed.');
        }
    };

    return (
        <div>
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <input name="firstName" placeholder="First Name" value={form.firstName} onChange={handleChange} required />
                <input name="lastName" placeholder="Last Name" value={form.lastName} onChange={handleChange} required />
                <select name="gender" value={form.gender} onChange={handleChange}>
                    <option value="MALE">Male</option>
                    <option value="FEMALE">Female</option>
                    <option value="OTHER">Other</option>
                </select>
                <input name="age" type="number" placeholder="Age" value={form.age} onChange={handleChange} required />
                <input name="email" type="email" placeholder="Email" value={form.email} onChange={handleChange} required />
                <input name="password" type="password" placeholder="Password" value={form.password} onChange={handleChange} required />
                <button type="submit">Register</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
}
