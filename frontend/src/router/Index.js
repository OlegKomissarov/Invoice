import Vue from 'vue';
import VueRouter from 'vue-router';
import HomePage from '../components/HomePage.vue';
import AllInvoices from '../components/InvoicesListPage/InvoicesListPage.vue';
import NotFound from '../errors/NotFound.vue';
import Profile from '../components/ProfilePage/ProfilePage.vue';
import Login from '../components/Login.vue';
import Signup from '../components/Signup.vue';

Vue.use(VueRouter);

const routes = [
    {
        path: '/invoice/:invoiceId',
        name: 'invoice:show',
        component: HomePage,
        meta: {
            title: 'invoice'
        },
        props: true
    },
    {
        path: '/invoice',
        name: 'all-invoices',
        component: AllInvoices,
        meta: {
            title: 'all invoices'
        },
        props: true
    },
    {
        path: '/profile',
        name: 'profile:show',
        component: Profile,
        meta: {
            title: 'profile'
        },
        props: true
    },
    {
        path: '/login',
        name: 'login',
        component: Login,
        meta: {
            title: 'login'
        },
        props: true
    },
    {
        path: '/signup',
        name: 'signup',
        component: Signup,
        meta: {
            title: 'signup'
        },
        props: true
    },
    {
        path: '*',
        name: 'not found',
        component: NotFound,
        meta: {
            title: 'not found'
        }
    }
];

const router = new VueRouter({
    routes,
    mode: 'history'
});

router.beforeEach((to, from, next) => {
    if (to.path === '/') {
        if (localStorage.getItem('userToken')) {
            next({name: 'all-invoices'});
        } else {
            next({name: 'login'});
        }
    }
    next();
});

export default router;
