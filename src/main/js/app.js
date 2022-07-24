import React, { useState } from 'react';
import { createRoot } from 'react-dom/client';
import axios from 'axios';

const App = () => {
    const [data, setData] = useState([]); // Set data to Array of integers
    const [inputValue, setInputValue] = useState('');

    const generatePrimes = () => {
        axios.get('http://localhost:8088/primes/' + inputValue).then(res => {
            setData(res.data);
        }).catch(e => {
            console.log(e);
            alert(e.message);
        })
    }

    return (
        <div>
            <input type='text' value={inputValue} onChange={e => setInputValue(e.target.value)}/>
            <button onClick={generatePrimes}>Send</button><br/>
            <br/>Generated Prime Numbers:<br/><br/>
            <table>
                <tbody>
                    <tr>
                    {data.map(d => (
                        <td key={d}>{d}  </td>
                    ))}
                    </tr>
                </tbody>
            </table>
        </div>
    )
}

const root = createRoot(document.getElementById('react'));
root.render(
    <App />
);