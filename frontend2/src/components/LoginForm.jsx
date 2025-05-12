// src/components/LoginForm.jsx
import { useState } from 'react';
import { loginUser } from '../api/auth';

export default function LoginForm() {
    const [form, setForm] = useState({ email: '', password: '' });
    const [message, setMessage] = useState('');
    const [user, setUser] = useState(null);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await loginUser(form);
            setMessage('Logged in successfully!');
            setUser(response.data.user);
            localStorage.setItem('token', response.data.token); // Store token
        } catch (error) {
            setMessage(error.response?.data?.message || 'Login failed.');
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <input name="email" type="email" placeholder="Email" value={form.email} onChange={handleChange} required />
                <input name="password" type="password" placeholder="Password" value={form.password} onChange={handleChange} required />
                <button type="submit">Login</button>
            </form>
            {message && <p>{message}</p>}
            {user && (
                <div>
                    <h3>Welcome, {user.firstName} {user.lastName}</h3>
                    <p>Email: {user.email}</p>
                    <p>Age: {user.age}</p>
                    <p>Gender: {user.gender}</p>
                </div>
            )}
        </div>
    );
}
