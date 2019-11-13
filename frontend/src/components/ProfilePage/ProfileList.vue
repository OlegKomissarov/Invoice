<template>
    <div class="profile-table-container">

        <span class="profile__header">Profile</span>

        <profile-list-item v-if="isLoaded"
                v-for="(profileAttribute, index) in profileList.fields"
                :key="profileAttribute.id"
                :profileAttribute="profileAttribute"
                @delete="deleteField(index)"
                @update="updateData()"
        ></profile-list-item>

        <div class="profile-add-button">
            <span class="add-button link"
                  :class="{'link_disabled': isAllowToAddField}"
                  @click="addBlock()"
                  v-if="isLoaded"
            >
                + Add
            </span>
        </div>

    </div>
</template>

<script>
    import ProfileListItem from './ProfileListItem.vue';
    import guid from '../../utils/guid';
    import Bus from '../../bus';
    import axios from 'axios';
    import ProfileApi from '../../api/ProfileApi';
    import { mapState } from 'vuex';
    import storeProfile from '../../utils/storeProfile'

    export default {
        mixins: [storeProfile],
        components: {
            ProfileListItem
        },
        data () {
            return {
                isLoaded: false,
                profileList: []
            }
        },
        computed: {
            isAllowToAddField () {
                return this.profileList.fields[this.profileList.fields.length - 1] && !this.profileList.fields[this.profileList.fields.length - 1].field;
            },
            config () {
                return { headers: { Authorization: 'Bearer ' + localStorage.getItem('userToken') } };
            }
        },
        mounted () {
            this.fetchData();
        },
        methods: {
            fetchData () {
                this.isLoaded = false;
                ProfileApi.show(this.config)
                    .then(response => {
                        this.profileList = response.data;
                        this.profileList.fields = JSON.parse(response.data.fields);
                        this.isLoaded = true;
                    })
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                        this.isLoaded = true;
                    })
            },
            updateData () {
                let requestData = { fields: JSON.stringify(this.profileList.fields) };
                ProfileApi.update(requestData, this.config)
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    })
            },
            addBlock () {
                let emptyProfileItem =
                    {
                        field: '',
                        value: '',
                        isAllowToChange: true,
                        id: guid()
                    };
                this.profileList.fields.push(emptyProfileItem);
            },
            deleteField (index) {
                this.profileList.fields.splice(index, 1);
                this.updateData();
            }
        }
    }

</script>
