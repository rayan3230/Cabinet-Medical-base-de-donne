document.addEventListener('DOMContentLoaded', function() {
    // Smooth scrolling for navigation
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });

    // Feature cards animation on scroll
    const cards = document.querySelectorAll('.feature-card');
    const observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = 1;
                entry.target.style.transform = 'translateY(0)';
            }
        });
    });

    cards.forEach(card => {
        card.style.opacity = 0;
        card.style.transform = 'translateY(20px)';
        card.style.transition = 'all 0.6s ease-out';
        observer.observe(card);
    });

    // Image modal functionality
    const screenshots = document.querySelectorAll('.screenshot-grid img');
    const modal = document.createElement('div');
    modal.className = 'modal';
    document.body.appendChild(modal);

    screenshots.forEach(img => {
        img.addEventListener('click', () => {
            modal.innerHTML = `
                <span class="close">&times;</span>
                <img src="${img.src}" alt="${img.alt}">
            `;
            modal.style.display = 'flex';
        });
    });

    modal.addEventListener('click', (e) => {
        if (e.target.className === 'modal' || e.target.className === 'close') {
            modal.style.display = 'none';
        }
    });

    // Counter animation for statistics
    function animateCounter(element, target) {
        let current = 0;
        const increment = target / 100;
        const timer = setInterval(() => {
            current += increment;
            element.textContent = Math.floor(current);
            if (current >= target) {
                element.textContent = target;
                clearInterval(timer);
            }
        }, 20);
    }

    // Add statistics section dynamically
    const statsSection = document.createElement('section');
    statsSection.innerHTML = `
        <div class="statistics">
            <div class="stat-item">
                <h3 class="counter" data-target="1000">0</h3>
                <p>Patients Served</p>
            </div>
            <div class="stat-item">
                <h3 class="counter" data-target="50">0</h3>
                <p>Expert Doctors</p>
            </div>
            <div class="stat-item">
                <h3 class="counter" data-target="5000">0</h3>
                <p>Appointments</p>
            </div>
        </div>
    `;

    document.querySelector('#features').after(statsSection);

    // Animate counters when they come into view
    const counters = document.querySelectorAll('.counter');

    // Mobile menu toggle
    const menuButton = document.createElement('button');
    menuButton.className = 'menu-toggle';
    menuButton.innerHTML = '☰';
    document.querySelector('nav').appendChild(menuButton);

    menuButton.addEventListener('click', () => {
        document.querySelector('nav ul').classList.toggle('show');
    });

    // Add scroll reveal animations
    const scrollReveal = function() {
        const elements = document.querySelectorAll('.feature-card, .screenshot-grid img, .stat-item');
        elements.forEach(element => {
            const elementTop = element.getBoundingClientRect().top;
            const elementVisible = 150;
            
            if (elementTop < window.innerHeight - elementVisible) {
                element.classList.add('active');
            }
        });
    };

    window.addEventListener('scroll', scrollReveal);

    // Parallax effect for hero section
    window.addEventListener('scroll', function() {
        const hero = document.querySelector('.hero');
        const scrolled = window.pageYOffset;
        hero.style.backgroundPositionY = scrolled * 0.5 + 'px';
    });

    // Animated counter for statistics
    const animateValue = (element, start, end, duration) => {
        let startTimestamp = null;
        const step = (timestamp) => {
            if (!startTimestamp) startTimestamp = timestamp;
            const progress = Math.min((timestamp - startTimestamp) / duration, 1);
            element.innerHTML = Math.floor(progress * (end - start) + start);
            if (progress < 1) {
                window.requestAnimationFrame(step);
            }
        };
        window.requestAnimationFrame(step);
    };

    // Animate statistics when in view
    const observerOptions = {
        threshold: 0.5
    };

    const statsObserver = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const counter = entry.target;
                const target = parseInt(counter.getAttribute('data-target'));
                animateValue(counter, 0, target, 2000);
                statsObserver.unobserve(counter);
            }
        });
    }, observerOptions);

    document.querySelectorAll('.counter').forEach(counter => {
        statsObserver.observe(counter);
    });

    // Add typing effect to hero text
    const heroText = document.querySelector('.hero h1');
    const text = heroText.textContent;
    heroText.textContent = '';
    let i = 0;

    function typeWriter() {
        if (i < text.length) {
            heroText.textContent += text.charAt(i);
            i++;
            setTimeout(typeWriter, 100);
        }
    }

    setTimeout(typeWriter, 500);

    // Add particle background effect
    particlesJS('particles-js', {
        particles: {
            number: { value: 80 },
            color: { value: '#ffffff' },
            opacity: { value: 0.5 },
            size: { value: 3 },
            line_linked: {
                enable: true,
                distance: 150,
                color: '#ffffff',
                opacity: 0.4,
                width: 1
            },
            move: {
                enable: true,
                speed: 6
            }
        }
    });
}); 