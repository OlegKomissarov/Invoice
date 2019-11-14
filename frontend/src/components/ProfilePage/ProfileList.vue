<template>
    <div class="profile-table-container">

        <span class="profile__header">Profile</span>

        <profile-list-item v-if="isLoaded"
                v-for="(profileAttribute, index) in profile.fields"
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
    import ProfileApi from '../../api/ProfileApi';
    import storeProfile from '../../utils/storeProfile';
    import hardcodedProfiles from '../../assets/db/profile';

    export default {
        mixins: [storeProfile],
        components: {
            ProfileListItem
        },
        data () {
            return {
                isLoaded: false,
                profile: {}
            }
        },
        computed: {
            isAllowToAddField () {
                return this.profile.fields[this.profile.fields.length - 1]
                    && !this.profile.fields[this.profile.fields.length - 1].field;
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
                ProfileApi.showAll(this.config)
                    .then(response => {
                        this.profile = hardcodedProfiles;
                        // this.profile = response.data;
                        // this.profile.fields = JSON.parse(response.data.fields);
                        this.isLoaded = true;
                    })
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                        this.isLoaded = true;
                    });
            },
            updateData () {
                let requestData = { fields: JSON.stringify(this.profile.fields) };
                ProfileApi.update(requestData, this.config)
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
            },
            addBlock () {
                let emptyProfileItem =
                    {
                        field: '',
                        value: '',
                        isAllowToChange: true,
                        id: guid()
                    };
                this.profile.fields.push(emptyProfileItem);
            },
            deleteField (index) {
                this.profile.fields.splice(index, 1);
                this.updateData();
            }
        }
    };
</script>
